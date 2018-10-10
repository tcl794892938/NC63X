package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import nc.vo.zl.tcl_contract.ContractHouseVO;
import nc.vo.zl.tcl_contract.ContractVO;
import nc.vo.zl.tcl_contract.ContractYwcfVO;

public class SendRecYwcfAction extends NCAction {
	
	private static final long serialVersionUID = -4869202750729279045L;
	
	public SendRecYwcfAction(){
		this.setCode("sendrecywcf");
		this.setBtnName("业务租金推应收");
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
		//校验是否已经生成了业务拆分应收单
		String sql="select count(1) from zl_recbill where nvl(dr,0)=0 and vsrcid='"+vo.getPk_contract()+"' and " +
				" vdef2='"+AbsEnumType.HT_YWCF+"' ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "已经生成过应收单，不可再次生成！");
			return ;
		}
		
		ContractYwcfVO[] ywvos=aggvo.getChildYwcfVO();
		if(ywvos==null||ywvos.length<=0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "无业务拆分数据！");
			return ;
		}
		
		//查询该合同房产对应的楼栋
		Map<String, String> map=new HashMap<String, String>();
		ContractHouseVO[] hvos=aggvo.getChildHouseVO();
		for(ContractHouseVO hvo:hvos){
			map.put(hvo.getPk_house(), hvo.getPk_building());
		}
		
		//生成应收单
		List<AggRecbillVO> list=new ArrayList<AggRecbillVO>();
		//AggRecbillVO[] aggvos=new AggRecbillVO[ywvos.length];
		for(int i=0;i<ywvos.length;i++){
			ContractYwcfVO yvo=ywvos[i];
			
			if(!yvo.getIs_qc().booleanValue()){
				//构造应收单
				AggRecbillVO agg=new AggRecbillVO();
				RecbillVO recvo=new RecbillVO();
				
				recvo.setPk_group(vo.getPk_group());
				recvo.setPk_org(vo.getPk_org());
				recvo.setPk_org_v(vo.getPk_org_v());
				recvo.setPk_project(vo.getPk_project());
				recvo.setPk_customer(yvo.getPk_customer());
				recvo.setPk_billtype("0001ZZ1000000001RHPI");
				recvo.setBilltypecode("H620");
				recvo.setVbillstatus(1);
				recvo.setPk_costproject(yvo.getPk_costproject());
				recvo.setGatherdate(yvo.getDrecdate());
				
				recvo.setCaccountperiod(yvo.getPk_month());
				recvo.setNrecmoney(yvo.getNysmny());
				recvo.setVsrcid(vo.getPk_contract());
				recvo.setVsrctype("0001ZZ1000000001SNDJ");
				recvo.setDbilldate(new UFDate());
				recvo.setCreator(model.getContext().getPk_loginUser());
				recvo.setCreationtime(new UFDateTime());
				recvo.setApprover(model.getContext().getPk_loginUser());
				recvo.setApprovetime(new UFDateTime());
				recvo.setDr(0);
				recvo.setVdef1(yvo.getPk_contract_ywcf());
				recvo.setVdef2(AbsEnumType.HT_YWCF);
				recvo.setNtaxrate(new UFDouble(vo.getTaxstyle()));
				recvo.setNnotaxmoney(yvo.getNysmny().div(100+vo.getTaxstyle()).multiply(100));
				recvo.setNtaxmoney(recvo.getNrecmoney().sub(recvo.getNnotaxmoney()));
				
				recvo.setBegindate(yvo.getDstartdate());
				recvo.setEnddate(yvo.getDenddate());
				recvo.setPk_building(map.get(yvo.getPk_house()));
				recvo.setPk_house(yvo.getPk_house());

				agg.setParentVO(recvo);
				list.add(agg);
			}
			
		}
		
		if(list.size()==0){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "所有业务租金明细为期初，无需推送应收！");
			return;
		}else{
			ICwf_recbillMaintain irm=NCLocator.getInstance().lookup(ICwf_recbillMaintain.class);
			irm.insert(list.toArray(new AggRecbillVO[0]), null);
			
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "生成成功！");
		}
		
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
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		
		AggContractVO aggvo=(AggContractVO)obj;
		Integer it=aggvo.getParentVO().getVbillstatus();
		if(it==1){
			return true;
		}
		
		return false;
	}
	
}
