package nc.ui.zl.cwf_sales.ace.handler;

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
				UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem(e.getKey()).getComponent();
				Object obj = billform.getBillCardPanel().getHeadItem("pk_org")
						.getValueObject();
				/*IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				//用户表的身份字段是人员基本信息表的主键
				String get_pk_psndoc="select pk_base_doc from sm_user where nvl(dr,0)=0 and cuserid='"+AppContext.getInstance().getPkUser()+"'";
				Object pk_base_doc=iQ.executeQuery(get_pk_psndoc, new ColumnProcessor());
				String procontrol="1=1";
				if(!getStrObj(pk_base_doc).equals("")){
					procontrol = "pk_project in(select c.pk_project from zl_projectcontrol c "+
				              "where c.pk_projectcontrol in (select b.pk_projectcontrol from "+
						      "zl_projectcontrol_b b where b.usercode = '"+getStrObj(pk_base_doc)+"'))";
				}*/
				ref.getRefModel().addWherePart(" and pk_org ='" + obj+"'");
		}

	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
