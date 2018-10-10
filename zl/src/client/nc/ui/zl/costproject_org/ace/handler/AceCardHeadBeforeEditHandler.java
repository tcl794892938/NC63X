package nc.ui.zl.costproject_org.ace.handler;

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
		
		
		if(e.getKey().equals("pk_incomepro")){
			System.out.println("qwe");
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().getRefSql();
			//ref.getRefModel().addWherePart("  or 1=1");
			System.out.println("qweee");
		}
		
		
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}