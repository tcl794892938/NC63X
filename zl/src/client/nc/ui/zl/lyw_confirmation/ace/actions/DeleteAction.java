package nc.ui.zl.lyw_confirmation.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.zl.lyw_billconfirmed.BillconfirmedBVO;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class DeleteAction extends DeleteScriptAction {
	public BillForm billForm;

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		AggConfirmationVO aggvo = (AggConfirmationVO)getModel().getSelectedData();
		if(aggvo==null){
			return;
		}
		ConfirmationVO vo = aggvo.getParentVO();
		if(!getStgObj(vo.getIsadd()).equals("Y")){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "该单据不是自制，不可删除！");
			return;
		}
		
		
		super.doAction(e);
	}
	
	
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}
