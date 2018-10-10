package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyAddLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	private ShowUpableBillForm billform;

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj1 = getBillform().getBillCardPanel().getCurrentBodyTableCode();
		String obj=obj1==null?"":obj1.toString();
		if(obj.equals("pk_customerfcxx")||obj.equals("pk_customerqjfy")
				||obj.equals("pk_customerwxfw")||obj.equals("pk_customercar")){
			MessageDialog.showHintDlg(billform, "提示", "当前表体不可新增数据！");
			return;
		}
		
		super.doAction(e);
	}

}
