package nc.ui.zl.ld_carcontract.ace.action;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.ValidationException;

public class BodyAddAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction() {
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "ÌבÊ¾", e.getMessage());
			return ;
		}
		String tabcode=billform.getBillCardPanel().getCurrentBodyTableCode();
		
		super.doAction();
		if(tabcode.equals("pk_carcontract_b")){
			Object customer = billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			Object obj = getBillform().getBillCardPanel()
					.getBillTable("pk_carcontract_b").getRowCount();
			Integer rowcount = (Integer) (obj == null ? "" : obj);
			billform.getBillCardPanel().getBillModel("pk_carcontract_b").setValueAt(customer, rowcount-1, "pk_customer");
		}
		billform.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
