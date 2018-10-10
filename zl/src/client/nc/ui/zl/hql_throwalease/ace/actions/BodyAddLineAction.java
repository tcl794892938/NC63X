package nc.ui.zl.hql_throwalease.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDouble;

public class BodyAddLineAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2421197157964435630L;
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction() {
		super.doAction();
		String tablecode=bill.getBillCardPanel().getCurrentBodyTableCode();
		if(tablecode.equals("pk_throwalease_zqfyqs")){
			Integer row=bill.getBillCardPanel().getBillModel("pk_throwalease_khfc").getRowCount();
			Integer row2=bill.getBillCardPanel().getBillModel("pk_throwalease_zqfyqs").getRowCount();
			Object pk_customer=bill.getBillCardPanel().getBillModel("pk_throwalease_khfc").getValueObjectAt(0, "pk_customer");
			Object pk_cus=((DefaultConstEnum)pk_customer).getValue();
			bill.getBillCardPanel().getBillModel("pk_throwalease_zqfyqs").setValueAt(pk_cus, row2-1, "pk_customer");
			bill.getBillCardPanel().getBillModel().setValueAt(new UFDouble(0), row2-1, "nskmny");
			BillModel bm = bill.getBillCardPanel().getBillModel();
			bm.loadLoadRelationItemValue();
			
		}
	}

	@Override
	protected boolean doBeforeAction(ActionEvent e) {
		String tablecode=bill.getBillCardPanel().getCurrentBodyTableCode();
		if(tablecode.equals("pk_throwalease_zqfyqs")){
			Integer row=bill.getBillCardPanel().getBillModel("pk_throwalease_khfc").getRowCount();
			Integer row2=bill.getBillCardPanel().getBillModel("pk_throwalease_zqfyqs").getRowCount();
			if(row2>=row){
				MessageDialog.showErrorDlg(bill, "提示", "表体房产数量超出！");
				return false;
			}
			
		}
		
		return true;
	}
	
}
