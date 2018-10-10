package nc.ui.zl.hql_entryacceptance.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		// TODO 自动生成的方法存根
		e.setReturnValue(true);

		if(e.getKey().equals("pk_jt_acceptance")){
			Object pk_pro = e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			int row = e.getBillCardPanel().getRowCount();
			String cond = "and pk_acceptance not in (";
			String s = "";
			for(int i = 0;i < row ;i++){
				if(e.getBillCardPanel().getBodyValueAt(i, "pk_jt_acceptance")!=null){
					s += "'"+e.getBillCardPanel().getBodyValueAt(i, "pk_jt_acceptance")+"'";
					if(i+1 != row && e.getBillCardPanel().getBodyValueAt(i+1, "pk_jt_acceptance")!=null){
						s += ",";
					}
				}
			}
			if(s.equals("")){
				cond = "";
			}else {
				cond += s+")";
			}
			ref.getRefModel().addWherePart(" and (pk_project = '"+pk_pro+"' or pk_org='"+pk_org+"')"+cond);
			ref.getRefModel().getRefSql();
		}
	}

}
