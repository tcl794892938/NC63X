package nc.ui.zl.ly_bslist.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AceCardHeadBeforeEditHandler implements
		IAppEventHandler<CardHeadTailBeforeEditEvent> {

	private ShowUpableBillForm billform;

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {

		e.setReturnValue(Boolean.TRUE);

		if (e.getKey().equals("pk_project")) {
			try {
				UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
				Object obj = billform.getBillCardPanel().getHeadItem("pk_org")
						.getValueObject();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				//用户表的身份字段是人员基本信息表的主键
				String count="select count(*) from zl_projectcontrol_b where nvl(dr,0)=0 and pk_projectcontrol " +
						"in (select p.pk_projectcontrol from zl_projectcontrol p where nvl(p.dr,0)=0 and " +
						"p.pk_org='"+obj+"') and usercode = (select s.pk_base_doc from sm_user s " +
								"where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')";
				Integer count1=(Integer) iQ.executeQuery(count, new ColumnProcessor());
				String procontrol = "1=1";
				if(count1>0){
					procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
							"where nvl(c.dr,0)=0 and c.pk_projectcontrol in (select b.pk_projectcontrol from "+
							"zl_projectcontrol_b b where nvl(b.dr,0)=0 and b.usercode = " +
							"(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')))";
				}
				ref.getRefModel().addWherePart(" and pk_org ='" + obj + "' and "+procontrol);
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getKey().equals("khname")){
			Object pk_pro=billform.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane refp=(UIRefPane)billform.getBillCardPanel().getHeadItem("khname").getComponent();
			refp.getRefModel().addWherePart(" and exists(select 1 from zl_customer_zzxm m where m.pk_customer=zl_customer.pk_customer and m.pk_project='"+pk_pro+"') ");
		}
		
		if (e.getKey().equals("fcname")) {
			BillItem bt = e.getBillCardPanel().getHeadItem(e.getKey());
			UIRefPane refp = (UIRefPane) bt.getComponent();
			Object pk_customer = billform.getBillCardPanel()
					.getHeadItem("khname").getValueObject();
			refp.getRefModel().addWherePart(
					"and pk_housesource in (select fcname from zl_customer_fcxx where nvl(dr,0)=0 and pk_customer='"
							+ getStgObj(pk_customer) + "')");
		}

	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}