package nc.ui.zl.ld_upcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.zl.ld_upcontract.UpcontractUVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction{

	private ShowUpableBillForm billForm;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		BillModel model = panel.getBillModel("pk_upcontract_u");
		int row = model.getRowCount();
		
		if(row<=0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "表体没有值，不可保存！");
			return;
		}
		super.doAction(e);
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}
