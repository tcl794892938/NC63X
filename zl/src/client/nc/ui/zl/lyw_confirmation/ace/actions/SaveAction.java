package nc.ui.zl.lyw_confirmation.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.pf.BillStatusEnum;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction {

	private ShowUpableBillForm billform;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().setHeadItem("vbillstatus", BillStatusEnum.FREE.value());
		super.doAction(e);
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
