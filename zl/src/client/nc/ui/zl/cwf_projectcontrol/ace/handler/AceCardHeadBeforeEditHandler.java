package nc.ui.zl.cwf_projectcontrol.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		
		
		e.setReturnValue(Boolean.TRUE);
		
		
		if(e.getKey().equals("pk_project")){
			BillItem bt=e.getBillCardPanel().getHeadItem(e.getKey());
			UIRefPane refp=(UIRefPane)bt.getComponent();
			Object obj=billForm.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			refp.getRefModel().addWherePart(" and pk_org ='"+obj+"' " );
		}
		
		
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}