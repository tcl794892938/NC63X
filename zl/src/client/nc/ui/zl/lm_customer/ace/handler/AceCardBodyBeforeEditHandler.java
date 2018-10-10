package nc.ui.zl.lm_customer.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		
		e.setReturnValue(true);
			
		if(e.getKey().equals("pk_project")){
				try {
					UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
					Object obj = billForm.getBillCardPanel().getHeadItem("pk_org")
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
					ref.getRefModel().addWherePart(" and pk_org ='" + obj+"' and "+procontrol);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
		}
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	
}
