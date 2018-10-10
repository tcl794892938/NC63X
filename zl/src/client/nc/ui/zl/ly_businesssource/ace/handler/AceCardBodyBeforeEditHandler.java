package nc.ui.zl.ly_businesssource.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AceCardBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {

	private ShowUpableBillForm billform;

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {

		e.setReturnValue(true);

		if (e.getKey().equals("procode")) {
				try {
					UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
					Object obj = billform.getBillCardPanel().getHeadItem("pk_org")
							.getValueObject();
					IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
					//用户表的身份字段是人员基本信息表的主键
					String count="select count(*) from zl_projectcontrol_b where nvl(dr,0)=0 and pk_projectcontrol " +
							"in (select p.pk_projectcontrol from zl_projectcontrol p where nvl(p.dr,0)=0 and " +
							"p.pk_org='"+getStgObj(obj)+"') and usercode = (select s.pk_base_doc from sm_user s " +
									"where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')";
					Integer count1=(Integer) iQ.executeQuery(count, new ColumnProcessor());
					String procontrol = "1=1";
					if(count1>0){
						procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
								"where nvl(c.dr,0)=0 and c.pk_projectcontrol in (select b.pk_projectcontrol from "+
								"zl_projectcontrol_b b where nvl(b.dr,0)=0 and b.usercode = " +
								"(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"')))";
					}
					ref.getRefModel().addWherePart(" and pk_org ='" + getStgObj(obj)+"' and "+procontrol);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
		}
		
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
