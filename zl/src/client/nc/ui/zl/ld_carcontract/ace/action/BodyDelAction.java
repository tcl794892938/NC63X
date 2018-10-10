package nc.ui.zl.ld_carcontract.ace.action;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyDelAction extends nc.ui.pubapp.uif2app.actions.BodyDelLineAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction() {
		BillModel modelc = billform.getBillCardPanel().getBillModel("pk_carcontract_c");
		BillModel modelf = billform.getBillCardPanel().getBillModel("pk_carcontract_f");
		int it=MessageDialog.showOkCancelDlg(billform, "提示", "删除给数据将清空相应的[业务拆分][财务拆分]信息，是否确认删除？");
		if(it!=UIDialog.ID_OK){
			return ;
		}
		modelc.clearBodyData();
		modelf.clearBodyData();
		super.doAction();
		super.doAction();
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
