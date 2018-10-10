package nc.ui.zl.cwf_carconedit.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

public class AceCardHeadBeforerEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		e.setReturnValue(true);
		if(e.getKey().equals("pk_customer")){
			Object pk_project=e.getBillCardPanel().getHeadItem("pk_poject").getValueObject();
			String m = "and exists (select b.* from zl_customer_zzxm b " +
					"where nvl(b.dr,0)=0 and b.pk_customer=zl_customer.pk_customer and b.pk_project in('"+getObj(pk_project)+"'))";
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(m);
		}
		
	}
	
	public String getObj(Object obj){
		
		return obj==null?"":obj.toString();
	}
}
