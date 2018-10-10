package nc.ui.zl.lyw_billconfirmed.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent>{

	
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		if(e.getKey().equals("usercode")){
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
			//ref.getRefModel().addWherePart(" and ");
			String cond = "and bd_psndoc.pk_psndoc not in (";
			String s = "";
			int row = e.getBillCardPanel().getRowCount();
			for(int i = 0;i < row;i++){
				if(e.getBillCardPanel().getBodyValueAt(i, "usercode") != null){
					s += "'"+e.getBillCardPanel().getBodyValueAt(i, "usercode")+"'";
					if(i+1 != row && e.getBillCardPanel().getBodyValueAt(i+1, "usercode")!=null){
						s += ",";
					}
				}
			}
			if(s.equals("")){
				cond = "";
			}else {
				cond += s+")";
			}
			ref.getRefModel().addWherePart(cond);
			ref.getRefModel().getWherePart();
			ref.getRefModel().getRefSql();
		}
	}
	
}
