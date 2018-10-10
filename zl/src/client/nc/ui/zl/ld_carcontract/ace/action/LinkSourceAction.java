package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ILd_carcontractMaintain;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.ld_carcontract.ace.config.HTHistoryDlg;

import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.zl.tcl_contract.AggContractVO;


public class LinkSourceAction extends NCAction{

	//private static final long serialVersionUID = -4869202750729279045L;
	
	public LinkSourceAction(){
		this.setCode("linksource");
		this.setBtnName("联查原始合同");
	}
	
	private BillManageModel model;
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		AggCarcontractVO aggvo=(AggCarcontractVO)obj;
		String pk=aggvo.getParentVO().getPk_carcontract();
		//查询原始pk
		String sql="select pk_carcontract from zl_carcontract where vbilltypecode='H524' and vsrcbid='"+pk+"' " +
				" and nvl(dr,0)=0 and version=0 ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object objpk=iQ.executeQuery(sql, new ColumnProcessor());
		if(objpk==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "原始合同查询异常，无原始数据！");
			return ;
		}
		//调用接口
		ILd_carcontractMaintain itc=NCLocator.getInstance().lookup(ILd_carcontractMaintain.class);
		AggCarcontractVO newaggvo=itc.queryCHTbyPK(objpk.toString());
		HTHistoryDlg dlg=new HTHistoryDlg(model.getContext().getEntranceUI());
		dlg.initValue(newaggvo);
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
		
		AggCarcontractVO aggvo=(AggCarcontractVO)obj;
		Integer it=aggvo.getParentVO().getVbillstatus();
		if(it==1){
			return true;
		}
		
		return false;
	}

}
