package nc.ui.zl.lyw_confirmation.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.zl.lyw_confirmation.ConfirmationVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggConfirmationVO aggvo = (AggConfirmationVO)getModel().getSelectedData();
		if(aggvo==null){
			return;
		}
		ConfirmationVO vo = aggvo.getParentVO();
		if(!getStgObj(vo.getIsadd()).equals("Y")){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "该单据不是自制，不可修改！");
			return;
		}
		
		
	
		super.doAction(e);
	}
	
	
	
	
	
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	
}
