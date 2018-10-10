package nc.ui.zl.cwf_gather.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;

public class BodyDelLineAction extends nc.ui.pubapp.uif2app.actions.BodyDelLineAction{
	
	private static final long serialVersionUID = 8823267294018118701L;
	private ShowUpableBillForm billform;

	@Override
	public void doAction() {
		
//		try {
//			billform.getBillCardPanel().stopEditing();
//			billform.getBillCardPanel().dataNotNullValidate();
//		} catch (ValidationException e) {
//			MessageDialog.showHintDlg(billform, "提示", e.getMessage());
//			return ;
//		}
		
		Object obj=billform.getBillCardPanel().getHeadItem("isadd").getValueObject();
		if(obj!=null && new UFBoolean(obj.toString()).booleanValue()){
			//do nothing
		}else{
			MessageDialog.showHintDlg(billform, "提示", "非自制单据不可删行！");
			return;
		}
		
		super.doAction();
		
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
