package nc.ui.zl.cwf_gather.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.vo.pub.lang.UFBoolean;

public class BodyAddLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	
	private static final long serialVersionUID = -1987097471377818277L;
	private ShowUpableBillForm billform;

	@Override
	public void doAction() {
		
//		try {
//			billform.getBillCardPanel().stopEditing();
//			billform.getBillCardPanel().dataNotNullValidate();
//		} catch (ValidationException e) {
//			MessageDialog.showHintDlg(billform, "��ʾ", e.getMessage());
//			return ;
//		}
		
		Object obj=billform.getBillCardPanel().getHeadItem("isadd").getValueObject();
		if(obj!=null && new UFBoolean(obj.toString()).booleanValue()){
			//do nothing
		}else{
			MessageDialog.showHintDlg(billform, "��ʾ", "�����Ƶ��ݲ������У�");
			return;
		}
		
		super.doAction();
		
		Integer row=billform.getBillCardPanel().getBillModel().getRowCount();
		billform.getBillCardPanel().setBodyValueAt(AbsEnumType.HTtaxrate2, row-1, "ntaxrate");
		
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
}
