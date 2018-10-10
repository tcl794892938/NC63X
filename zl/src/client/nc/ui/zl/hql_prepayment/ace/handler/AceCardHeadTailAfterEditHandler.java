package nc.ui.zl.hql_prepayment.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class AceCardHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("pk_project")){
			if(e.getValue()==null){
				return;
			}
			if(!e.getKey().equals(e.getOldValue())&&e.getOldValue()!=null){
				e.getBillCardPanel().setHeadItem("pk_customer", null);
				Integer rowcount=e.getBillCardPanel().getBillTable().getRowCount();
				e.getBillCardPanel().getBillModel().delLine(new int[rowcount]);
			}
		}
		if(e.getKey().equals("dysdate")){
			Object dateObj = e.getValue();
			if(dateObj==null){
				return;
			}
			if(dateObj != null){
				String d = dateObj.toString();
				UFDate date = new UFDate(dateObj.toString());
				String kjny = date.getYear()+"-"+date.getStrMonth();
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
						     "nvl(dr,0)=0 and (begindate <= '"+d+"' and enddate >= '"+d+"')";
				
				try {
					Object pk = iQ.executeQuery(sql, new ColumnProcessor());
					if(pk == null){
						return;
					}
					int row =e.getBillCardPanel().getBillModel("pk_prepayment_b").getRowCount();
					for(int i = 0;i < row;i++){
						e.getBillCardPanel().setBodyValueAt(dateObj, i, "dysdate");
						e.getBillCardPanel().setBodyValueAt(pk, i, "caccountperiod");
					}
					//System.out.println(e.getBillCardPanel().getBodyValueAt(row, "caccountperiod"));
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
	}

}
