package nc.ui.zl.ld_upcontract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		// TODO 自动生成的方法存根
		e.setReturnValue(true);
		
		if(e.getKey().equals("pk_project")){	
			
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			String user = e.getContext().getPk_loginUser();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String sql_1 = "select pk_psndoc from sm_user where nvl(dr,0)=0 and cuserid='"+user+"'";
			Object obj = null;
			try {
				obj=iQ.executeQuery(sql_1, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

			String str = "";
			
			if(obj!=null){
				
				str = "and pk_project in ("+"select pk_project from zl_projectcontrol zp ,zl_projectcontrol_b zpb"+
						" where zp.pk_projectcontrol=zpb.pk_projectcontrol and zpb.usercode='"+getStgObj(obj)+"'"+")";
			}
			
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' "+str);
			/*ref.getRefModel().getWherePart();
			ref.getRefModel().getRefSql();*/
		}
		if(e.getKey().equals("pk_customer")){
			Object pk_project=e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			String m = "and exists (select b.* from zl_customer_zzxm b " +
					"where nvl(b.dr,0)=0 and b.pk_customer=zl_customer.pk_customer and b.pk_project in('"+pk_project+"'))";
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(m);
		}
	}
	
	//字符串封装
			public String getStgObj(Object obj){
				return obj==null?"":obj.toString();
			}

}
