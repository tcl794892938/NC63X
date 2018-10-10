package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ITcl_contractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.tcl_contract.ace.config.HTHistoryDlg;
import nc.vo.zl.tcl_contract.AggContractVO;

public class LinkSourceAction extends NCAction {
	
	private static final long serialVersionUID = -4869202750729279045L;
	
	public LinkSourceAction(){
		this.setCode("linksource");
		this.setBtnName("联查原始合同");
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
		String pk=aggvo.getParentVO().getPk_contract();
		//查询原始pk
		String sql="select pk_contract from zl_contract where vbilltypecode='H420' and vsrcid='"+pk+"' " +
				" and nvl(dr,0)=0 and version=0 ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object objpk=iQ.executeQuery(sql, new ColumnProcessor());
		if(objpk==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "原始合同查询异常，无原始数据！");
			return ;
		}
		//调用接口
		ITcl_contractMaintain itm=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
		AggContractVO newaggvo=itm.queryHTbyPK(objpk.toString());
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
		
		AggContractVO aggvo=(AggContractVO)obj;
		Integer it=aggvo.getParentVO().getVbillstatus();
		if(it==1){
			return true;
		}
		
		return false;
	}
	
}
