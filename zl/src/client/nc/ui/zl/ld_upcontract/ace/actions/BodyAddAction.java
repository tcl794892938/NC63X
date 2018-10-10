package nc.ui.zl.ld_upcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;

public class BodyAddAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction() {
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "提示", e.getMessage());
			return ;
		}
		super.doAction();
		Object customer = billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
		Object startdate = billform.getBillCardPanel().getHeadItem("startdate").getValueObject();
		Object enddate = billform.getBillCardPanel().getHeadItem("enddate").getValueObject();
		//receivemoney应收金额
		Object obj1 = billform.getBillCardPanel().getHeadItem("allrent").getValueObject();
		UFDouble allrent =  new UFDouble(obj1.toString());
		Object  obj2= billform.getBillCardPanel().getHeadItem("taxrate").getValueObject();
		Integer taxrate = Integer.valueOf(obj2.toString());
		
		
		//税额
		UFDouble tax = allrent.multiply(taxrate);
		UFDouble taxm = tax.div(100);
		//无税金额
		UFDouble freetax = allrent.sub(taxm);
		
		
		Object obj = getBillform().getBillCardPanel()
				.getBillTable("pk_upcontract_u").getRowCount();
		Integer rowcount = (Integer) (obj == null ? "" : obj);
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(customer, rowcount-1, "pk_customer");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(startdate, rowcount-1, "mstartdate");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(enddate, rowcount-1, "menddate");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(startdate, rowcount-1, "moneydate");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(allrent, rowcount-1, "receivemoney");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(freetax, rowcount-1, "freetaxmoney");
		billform.getBillCardPanel().getBillModel("pk_upcontract_u").setValueAt(taxm, rowcount-1, "taxmoney");
		
		
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
