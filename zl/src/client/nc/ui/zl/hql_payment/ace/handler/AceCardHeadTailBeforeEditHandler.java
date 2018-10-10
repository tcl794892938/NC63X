package nc.ui.zl.hql_payment.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AceCardHeadTailBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		// TODO 自动生成的方法存根
		e.setReturnValue(true);
		
		if(e.getKey().equals("pk_project")){
			try {
				UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
				Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String count="select count(*) from zl_projectcontrol_b where nvl(dr,0)=0 and pk_projectcontrol " +
						"in (select p.pk_projectcontrol from zl_projectcontrol p where nvl(p.dr,0)=0 and " +
						"p.pk_org='"+pk_org+"') and usercode = (select s.pk_base_doc from sm_user s " +
								"where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')";
				Integer count1=(Integer) iQ.executeQuery(count, new ColumnProcessor());
				String procontrol = "1=1";
				if(count1>0){
					procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
							"where nvl(c.dr,0)=0 and c.pk_projectcontrol in (select b.pk_projectcontrol from "+
							"zl_projectcontrol_b b where nvl(b.dr,0)=0 and b.usercode = " +
							"(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')))";
				}
				ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' and "+ procontrol);
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
		}
		
		/*if(e.getKey().equals("pk_customer")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			Object pk_project = e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			Object user = e.getContext().getPk_loginUser();
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			String sql_user = "select count(*) from zl_sales s where " +
					          "nvl(s.dr,0)=0 and s.username in (select u.pk_psndoc from " +
					          "sm_user u where nvl(u.dr,0)=0 and u.cuserid = '"+user+"')";
			
			int count = 0;
			try {
				count = (Integer) iQ.executeQuery(sql_user, new ColumnProcessor());
			} catch (Exception ex) {
				// TODO 自动生成的 catch 块
				ex.printStackTrace();
			}
			String cond = "";
			if(count > 0){
				cond += "and zygw in (select u.pk_psndoc from " +
					          "sm_user u where nvl(u.dr,0)=0 and u.cuserid = '"+user+"') ";
			}
			cond += "and pk_customer in (select pk_customer from zl_customer_zzxm where " +
					      "nvl(dr,0)=0 and pk_project = ";
			if(pk_project == null){
				cond += "null";
			}else{
				cond += "'"+pk_project+"'";
			}
			cond += ")";
			
            UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' "+cond);
            ref.getRefModel().getWherePart();
			ref.getRefModel().getRefSql();
		}*/
		
	}

}
