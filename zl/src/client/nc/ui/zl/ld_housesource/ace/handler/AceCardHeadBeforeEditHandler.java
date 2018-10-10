package nc.ui.zl.ld_housesource.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;


public class AceCardHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	private ShowUpableBillForm billForm;
	
	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		
		e.setReturnValue(true);
		
		if(e.getKey().equals("projectname")){
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
		
		if(e.getKey().equals("buildname")){
			
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			
			Object pk_project=e.getBillCardPanel().getHeadItem("projectname").getValueObject();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' and pk_projectid='"+pk_project+"'");
		}
		
		
		if(e.getKey().equals("pk_familyfile")){
			
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			
			Object pk_project=e.getBillCardPanel().getHeadItem("projectname").getValueObject();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' and pk_projectid='"+pk_project+"'");
		}
		
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	
	

}
