package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractBzjVO;
import nc.vo.zl.tcl_contract.ContractVO;

public class SendRecBzjAction extends NCAction {
	
	private static final long serialVersionUID = -4869202750729279045L;
	
	public SendRecBzjAction(){
		this.setCode("sendrecbzj");
		this.setBtnName("保证金推应收");
	}
	
	private BillManageModel model;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		AggContractVO aggvo=(AggContractVO)obj;
		
		ContractVO vo=aggvo.getParentVO();
		//校验是否已经生成了保证金应收单
		String sql="select count(1) from zl_recbill where nvl(dr,0)=0 and vsrcid='"+vo.getPk_contract()+"' and " +
				" vdef2='"+AbsEnumType.HT_BZJ+"' ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "已经生成了保证金应收单，不可再次生成！");
			return ;
		}
		
		ContractBzjVO bzvo=aggvo.getChildBzjVO()[0];
		//构造应收单
		RecbillVO recvo=new RecbillVO();
		recvo.setPk_group(vo.getPk_group());
		recvo.setPk_org(vo.getPk_org());
		recvo.setPk_org_v(vo.getPk_org_v());
		recvo.setPk_project(vo.getPk_project());
		recvo.setPk_customer(vo.getPk_customer());
		recvo.setPk_billtype("0001ZZ1000000001RHPI");
		recvo.setBilltypecode("H620");
		recvo.setVbillstatus(1);
		recvo.setPk_costproject(bzvo.getPk_costproject());
		recvo.setGatherdate(bzvo.getDrecdate());
		//获取会计月份
		String month=bzvo.getDrecdate().toString().substring(0, 7);
		String sqlmon="select h.pk_accperiodmonth from bd_accperiodmonth h " +
				"where h.yearmth='"+month+"' and nvl(dr,0)=0 and " +
				"pk_accperiodscheme='"+AbsEnumType.Period+"'";
		Object objmon=iQ.executeQuery(sqlmon, new ColumnProcessor());
		recvo.setCaccountperiod(objmon==null?"":objmon.toString());
		recvo.setNrecmoney(bzvo.getNysmny());
		recvo.setNnotaxmoney(bzvo.getNysmny());
		recvo.setNtaxmoney(new UFDouble(0));
		recvo.setVsrcid(vo.getPk_contract());
		recvo.setVsrctype("0001ZZ1000000001SNDJ");
		recvo.setDbilldate(new UFDate());
		recvo.setCreator(model.getContext().getPk_loginUser());
		recvo.setCreationtime(new UFDateTime());
		recvo.setApprover(model.getContext().getPk_loginUser());
		recvo.setApprovetime(new UFDateTime());
		recvo.setDr(0);
		recvo.setVdef1(bzvo.getPk_contract_bzj());
		recvo.setVdef2(AbsEnumType.HT_BZJ);
		recvo.setNtaxrate(new UFDouble(0));
		AggRecbillVO rggvo=new AggRecbillVO();
		rggvo.setParentVO(recvo);
		
		ICwf_recbillMaintain irm=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
		irm.insert(new AggRecbillVO[]{rggvo}, null);
		
		MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "生成成功！");
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		
		/*Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		
		AggContractVO aggvo=(AggContractVO)obj;
		Integer it=aggvo.getParentVO().getVbillstatus();
		if(it==-1){
			return true;
		}*/
		
		return true;
	}
	
}
