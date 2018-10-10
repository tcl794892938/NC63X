package nc.ui.zl.ly_zylist.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		//System.out.println("123");
		if(e.getKey().equals("finishtime")){
			try {
				if(e.getValue()==null){
					return;
				}
				
				UFDate bxdate=(UFDate) e.getBillCardPanel().getHeadItem("bxdate").getValueObject();
				Object finish=e.getValue();
				UFDate fdate=UFDate.getDate(finish.toString());
				if(bxdate.afterDate(fdate)){
					MessageDialog.showErrorDlg(e.getContext().getEntranceUI(), "提示", "完成时间不能在报修时间之前");
					e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
					return;
				}
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
						     "nvl(dr,0)=0 and (begindate <= '"+finish+"' and enddate >= '"+finish+"')";
				Object pk = iQ.executeQuery(sql, new ColumnProcessor());
				e.getBillCardPanel().setBodyValueAt(pk.toString(), e.getRow(), "caccountperiod");
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
