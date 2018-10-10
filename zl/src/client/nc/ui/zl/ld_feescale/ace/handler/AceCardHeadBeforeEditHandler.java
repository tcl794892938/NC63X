package nc.ui.zl.ld_feescale.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		
		e.setReturnValue(true);
		
		if(e.getKey().equals("chargeitem")){
			
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			Object pk_group=e.getBillCardPanel().getHeadItem("pk_group").getValueObject();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			
			ref.getRefModel().addWherePart(" and pk_org in('"+pk_org+"','"+pk_group+"')");
				
			ref.getRefModel().getRefSql();
		}
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
