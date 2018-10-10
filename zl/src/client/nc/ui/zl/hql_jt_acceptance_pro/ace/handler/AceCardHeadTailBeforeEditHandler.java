package nc.ui.zl.hql_jt_acceptance_pro.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
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
	}

}
