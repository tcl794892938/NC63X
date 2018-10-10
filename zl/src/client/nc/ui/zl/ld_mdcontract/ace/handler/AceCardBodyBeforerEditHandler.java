package nc.ui.zl.ld_mdcontract.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class AceCardBodyBeforerEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		
		if(e.getKey().equals("pk_costproject")){
			
			Object obj=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane refp=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			refp.getRefModel().addWherePart(" and (nvl(vdef1,0)=0 or pk_org='"+getStgObj(obj)+"')");
			
		}
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
