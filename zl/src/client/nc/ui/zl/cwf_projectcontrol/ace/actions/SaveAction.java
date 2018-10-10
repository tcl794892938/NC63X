package nc.ui.zl.cwf_projectcontrol.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.lang.UFDouble;

public class SaveAction extends DifferentVOSaveAction{
	public BillForm billform;


	public BillForm getBillform() {
	return billform;
}

public void setBillform(BillForm billform) {
	this.billform = billform;
}

@Override
public void doAction(ActionEvent e) throws Exception {
	billform.getBillCardPanel().stopEditing();
	billform.getBillCardPanel().dataNotNullValidate();
	
	
		super.doAction(e);
}

	private UFDouble getUfd(UFDouble ud){
		return ud==null?new UFDouble(0):ud;
	}
}
