package nc.ui.zl.hql_jt_acceptance.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AddAction1 extends AddAction {

	private ShowUpableBillForm billform;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().getHeadItem("code").setEdit(true);
		super.doAction(e);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
