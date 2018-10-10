package nc.ui.zl.lm_customer.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadBeforeEditHandler implements
		IAppEventHandler<CardHeadTailBeforeEditEvent> {

	private ShowUpableBillForm billform;

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {

		e.setReturnValue(Boolean.TRUE);
		
		if(e.getKey().equals("customertype")){
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			Object pk_org = billform.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			ref.getRefModel().addWherePart(" and (pk_org ='" + getStgObj(pk_org)+"' or nvl(vdef1,0)=0)");
		}

	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}