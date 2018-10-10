package nc.ui.zl.hql_payment.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyAddLineAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction {
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		//Object customer = bill.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
		Object project = bill.getBillCardPanel().getHeadItem("pk_project").getValueObject();
		//Object dateObj = bill.getBillCardPanel().getHeadItem("dysdate").getValueObject();
		if(project==null){
			MessageDialog.showHintDlg(bill, "提示", "表头项目不能为空！");
			return;
		}
		super.doAction(e);
	}
	
}
