package nc.ui.zl.ly_hflist.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDate;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		if(e.getKey().equals("hfdate")){
			Object finish=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "finishtime");
			UFDate hfdate=(UFDate) e.getValue();
			UFDate wcdate=UFDate.getDate(finish.toString());
			if(wcdate.afterDate(hfdate)){
				MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "提示", "回访时间不能在完成时间之前");
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
				return;
			}
		}
		
		/*BillModel bm = e.getBillCardPanel().getBillModel();
		bm.loadLoadRelationItemValue();*/
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
