package nc.ui.zl.lm_customer.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

public class AceCardBodyAfterEditHandler implements
		IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// System.out.println("123");
		if (e.getKey().equals("pk_project")) {
			if (e.getRow() > 0) {
				for (int i = 0; i < e.getRow(); i++) {
					Object procode = e.getBillCardPanel().getBillModel()
							.getValueAt(i, "pk_project");
					Object procode1 = e.getBillCardPanel().getBillModel()
							.getValueAt(e.getRow(), "pk_project");
					if (getStgObj(procode).equals(getStgObj(procode1))) {
						MessageDialog.showHintDlg(e.getContext()
								.getEntranceUI(), "提示", "项目编码重复请重新选择！");
						e.getBillCardPanel().getBillModel().setValueAt(null, e.getRow(), "pk_project");
						return;
					}
				}
			}

		}

	}

	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}
