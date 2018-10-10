package nc.ui.zl.cwf_gather.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;

public class BodyInsLineAction extends BodyInsertLineAction {
	
	
	private static final long serialVersionUID = -6325844171198550428L;
	private ShowUpableBillForm billform;
	
	@Override
	public void doAction() {
//		try {
//		billform.getBillCardPanel().stopEditing();
//		billform.getBillCardPanel().dataNotNullValidate();
//	} catch (ValidationException e) {
//		MessageDialog.showHintDlg(billform, "提示", e.getMessage());
//		return ;
//	}
	
	Object obj=billform.getBillCardPanel().getHeadItem("isadd").getValueObject();
	if(obj!=null && new UFBoolean(obj.toString()).booleanValue()){
		//do nothing
	}else{
		MessageDialog.showHintDlg(billform, "提示", "非自制单据不可插入行！");
		return;
	}
	
	super.doAction();
	}

	@Override
	protected void afterLineInsert(int index) {
		super.afterLineInsert(index);
		billform.getBillCardPanel().setBodyValueAt(AbsEnumType.HTtaxrate3, index, "ntaxrate");
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

}
