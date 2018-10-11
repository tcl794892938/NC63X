package nc.ui.zl.hql_payment.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardHeadTailAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getKey().equals("pk_project")){
			if(e.getValue()==null){
				return;
			}
			if(!e.getValue().equals(e.getOldValue())&&e.getOldValue()!=null){
				Integer rowcount=e.getBillCardPanel().getBillTable().getRowCount();
				e.getBillCardPanel().getBillModel().delLine(new int[rowcount]);
			}
			
		}
		
		if(e.getKey().equals("dysdate")){
			Object dateObj = e.getValue();
			if(dateObj != null){
				String d = dateObj.toString();
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
						     "nvl(dr,0)=0 and (begindate <= '"+d+"' and enddate >= '"+d+"')";
				
				try {
					Object pk = iQ.executeQuery(sql, new ColumnProcessor());
					if(pk == null){
						return;
					}
					int row =e.getBillCardPanel().getBillModel("pk_payment_b").getRowCount();
					for(int i = 0;i < row;i++){
						e.getBillCardPanel().getBillModel("pk_payment_b").setValueAt(pk, i, "caccountperiod");
						//e.getBillCardPanel().setBodyValueAt(dateObj, i, "dysdate");
						e.getBillCardPanel().setBodyValueAt(pk, i, "caccountperiod");
					}
					//System.out.println(e.getBillCardPanel().getBodyValueAt(row, "caccountperiod"));
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			
		}
	}

}