package nc.ui.zl.hql_contracttype.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {
	private ShowUpableBillForm billForm;

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("code")){
			Integer len = e.getValue().toString().length();
			if(len > 8 || len % 2 != 0){
				MessageDialog.showHintDlg(billForm, "类型编码错误", "类型编码必须是2-2-2-2格式！");
				billForm.getBillCardPanel().setHeadItem("code", "");
			}
		}
	}
	
}
