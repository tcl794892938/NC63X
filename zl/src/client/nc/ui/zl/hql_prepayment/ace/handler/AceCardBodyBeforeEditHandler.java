package nc.ui.zl.hql_prepayment.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		// TODO 自动生成的方法存根
		e.setReturnValue(true);
		
		if(e.getKey().equals("pk_costproject")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
            UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
            ref.getRefModel().addWherePart("and (pk_org='"+pk_org+"' or nvl(vdef1,0)='0')");
            ref.getRefModel().getWherePart();
			ref.getRefModel().getRefSql();
		}
		if(e.getKey().equals("pk_customer")){
			Object pk_pro=e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(" and exists(select 1 from zl_customer_zzxm m where m.pk_customer=zl_customer.pk_customer and m.pk_project='"+pk_pro+"') ");
			ref.setMultiSelectedEnabled(true);
		}
	}

}
