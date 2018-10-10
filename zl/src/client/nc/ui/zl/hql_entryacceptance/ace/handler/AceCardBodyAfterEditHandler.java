package nc.ui.zl.hql_entryacceptance.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("pk_customer")){
 			e.getBillCardPanel().getBodyItem("pk_buildingfile").setEdit(true);
			e.getBillCardPanel().getBodyItem("pk_housesource").setEdit(true);
			
		}
		if(e.getKey().equals("entrydate")){
			Object obj = e.getBillCardPanel().getHeadItem("startday").getValueObject();
			Object obj2 = e.getBillCardPanel().getHeadItem("endday").getValueObject();
			Object obj3 = e.getBillCardPanel().getBodyItem("entrydate").getValueObject();
			if(obj == null || obj2 == null || obj3 == null){
				return;
			}
			UFDate start = new UFDate(obj.toString());
			UFDate end = new UFDate(obj2.toString());
			UFDate jcdate = new UFDate(obj3.toString());
			if(jcdate.beforeDate(start)||jcdate.afterDate(end)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "进场日期应在合同起始日到合同截止日之内！");
				e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			int row = e.getBillCardPanel().getRowCount();
			for(int i = 0;i < row;i++){
				e.getBillCardPanel().setBodyValueAt(jcdate, i, e.getKey());
			}
		}
		
		if(e.getKey().equals("pk_jt_acceptance")){
			if(e.getValue()!=null){
				try {
					String sql="select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and pk_parent='"+e.getValue()+"'";
					IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
					Integer count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
					if(count>0){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "进场验收项目需要选择最末级！");
						e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
						return ;
					}
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
