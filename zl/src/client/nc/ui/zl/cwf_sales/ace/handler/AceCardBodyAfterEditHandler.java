package nc.ui.zl.cwf_sales.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		//System.out.println("123");
		if(e.getKey().equals("procode")){
			try {
				String sql="select name from zl_project where nvl(dr,0)=0 and pk_project='"+e.getValue()+"'";
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object obj=iQ.executeQuery(sql, new ColumnProcessor());
				String name=obj==null?"":obj.toString();
				e.getBillCardPanel().getBillModel().setValueAt(name, e.getRow(), "proname");
			} catch (BusinessException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
			/*UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem("salesman").getComponent();
			ref.getRefModel();*/
		}
		
	}

}