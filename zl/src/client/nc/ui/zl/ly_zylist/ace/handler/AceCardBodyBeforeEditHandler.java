package nc.ui.zl.ly_zylist.ace.handler;


import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {

	private ShowUpableBillForm billform;

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {

		e.setReturnValue(true);

		if(e.getKey().equals("payproject")){
			Object pk_org=billform.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane ref=(UIRefPane)billform.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(" and (pk_org='"+pk_org+"' or nvl(vdef1,0)='0')");
		}

	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
