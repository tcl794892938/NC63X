package nc.ui.zl.cwf_gather.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
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
		
		if(e.getKey().equals("pk_project")){
			
			Object obj=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();

			try {
				UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				//用户表的身份字段是人员基本信息表的主键
				String get_pk_pro="select pk_projectcontrol from zl_projectcontrol_b where nvl(dr,0)=0 and " +
						"usercode=(select pk_base_doc from sm_user where nvl(dr,0)=0 and cuserid='"+AppContext.getInstance().getPkUser()+"')";
				List<Object> pk_pro=(List<Object>) iQ.executeQuery(get_pk_pro, new ArrayListProcessor());
				String procontrol="1=1";
				if(pk_pro.size()>0){
					String pros = "";
					for(int i=0;i<pk_pro.size();i++){
						Object[] pks=(Object[]) pk_pro.get(i);
						pros+="'"+pks[0]+"'";
						if(i<pk_pro.size()-1){
							pros+=",";
						}
					}
					procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
				              "where nvl(c.dr,0)=0 and c.pk_projectcontrol in ("+pros+"))";
				}
				ref.getRefModel().addWherePart(" and pk_org ='" + obj+"' and "+procontrol);
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			return;
	
		}
		if(e.getKey().equals("pk_customer")){
			Object pk_pro=billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getHeadItem("pk_customer").getComponent();
			refp.getRefModel().addWherePart(" and exists(select 1 from zl_customer_zzxm m where m.pk_customer=zl_customer.pk_customer and m.pk_project='"+pk_pro+"') ");
		}
		
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
}
