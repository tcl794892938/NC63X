package nc.ui.zl.ly_hflist.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyDelLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction {

	private ShowUpableBillForm billform;
	private BillManageModel model;

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getBillform().getBillCardPanel().getBillTable().getSelectedRow();
		Integer row=(Integer)(obj==null?"":obj);
		Object vdef1 = getBillform().getBillCardPanel().getBillModel().getValueAt(row, "vdef5");
		String s = vdef1 == null ? "" : vdef1.toString();
		if (s.equals("already")) {
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"该条数据是参照数据不可删除！");
			return;
		}
		
		super.doAction(e);
		
	}
	

}
