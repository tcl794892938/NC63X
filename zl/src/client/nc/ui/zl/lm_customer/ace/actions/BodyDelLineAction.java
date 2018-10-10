package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyDelLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction {

	private ShowUpableBillForm billform;

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getBillform().getBillCardPanel().getBillTable().getSelectedRow();
		Integer row=(Integer)(obj==null?"":obj);
		Object vdef1 = getBillform().getBillCardPanel().getBillModel().getValueAt(row, "vdef1");
		String s = vdef1 == null ? "" : vdef1.toString();
		if (s.equals("noedit")) {
			MessageDialog.showHintDlg(billform, "提示",
					"该条数据是生成数据不可删除！");
			return;
		}
		
		Object obj1 = getBillform().getBillCardPanel().getCurrentBodyTableCode();
		String obj2=obj1==null?"":obj1.toString();
		if(obj2.equals("pk_customerfcxx")||obj2.equals("pk_customerqjfy")
				||obj2.equals("pk_customerwxfw")||obj2.equals("pk_customercar")){
			MessageDialog.showHintDlg(billform, "提示", "当前表体不可删除数据！");
			return;
		}
		
		super.doAction(e);
		
	}
	

}
