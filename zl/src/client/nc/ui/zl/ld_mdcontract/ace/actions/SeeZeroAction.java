package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.zl.ILd_mdcontractMaintain;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.zl.ld_mdcontract.ace.config.HTHistoryDlg;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

public class SeeZeroAction extends NCAction {

	private BillManageModel model;
	
	public SeeZeroAction() {
		super();
		this.setCode("seezero");
		this.setBtnName("联查原始合同");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请选择一条数据！");
			return ;
		}
		
		AggMdcontractVO aggvo=(AggMdcontractVO)obj;
		String pk=aggvo.getParentVO().getVbillcode();
		//查询原始pk
		String sql="select pk_mdcontract from zl_mdcontract where vbillcode='"+pk+"' " +
				" and nvl(dr,0)=0 and version=0 ";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object objpk=iQ.executeQuery(sql, new ColumnProcessor());
		if(objpk==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "原始合同查询异常，无原始数据！");
			return ;
		}
		//调用接口
		ILd_mdcontractMaintain itm=NCLocator.getInstance().lookup(ILd_mdcontractMaintain.class);
		AggMdcontractVO newaggvo=itm.queryHTbyPK(objpk.toString());
		HTHistoryDlg dlg=new HTHistoryDlg(model.getContext().getEntranceUI());
		dlg.initValue(newaggvo);
	}
	
	
	
	@Override
	protected boolean isActionEnable() {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		AggMdcontractVO aggvo=(AggMdcontractVO)obj;
		Integer it=aggvo.getParentVO().getState();
		if(it==1){
			return true;
		}
		return false;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

}
