package nc.ui.zl.ld_carcontract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pub.BusinessException;

public class AceCardHeadBeforeEditHandler implements IAppEventHandler<CardHeadTailBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		
		e.setReturnValue(true);
		if(e.getKey().equals("pk_contracttype")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(" and (pk_org='"+pk_org+"' or nvl(vdef1,0)='0')");
		}
		
		if(e.getKey().equals("pk_customer")){
			Object project=e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			String m = "and exists (select b.* from zl_customer_zzxm b " +
					"where nvl(b.dr,0)=0 and b.pk_customer=zl_customer.pk_customer and b.pk_project in('"+project+"'))";
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(m);
		}
		
		if(e.getKey().equals("pk_project")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			String user = e.getContext().getPk_loginUser();
			
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			
			
			String sql_1 = "select pk_base_doc from sm_user where nvl(dr,0)=0 and cuserid='"+user+"'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Object obj = null;
			try {
				obj=iQ.executeQuery(sql_1, new ColumnProcessor());
			} catch (BusinessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

			String str = "";
			
			if(obj!=null){
				Object obj2 = null;
				String sql_2 = "select pk_project from zl_projectcontrol zp ,zl_projectcontrol_b zpb"+
						" where zp.pk_projectcontrol=zpb.pk_projectcontrol and zpb.usercode='"+getStgObj(obj)+"'";
				try {
					obj2=iQ.executeQuery(sql_2, new ColumnProcessor());
				} catch (BusinessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if(obj2!=null){
					str = "and pk_project in ("+sql_2+")";
				}
				
			}
			 
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' "+str);
		}
		
		if(e.getKey().equals("pk_costproject")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			ref.getRefModel().addWherePart(" and (pk_org='"+pk_org+"' or nvl(vdef1,0)='0')");
		}
		
		if(e.getKey().equals("pk_feescale")){
			Object pk_org=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getHeadItem(e.getKey()).getComponent();
			Object pk_costproject = e.getBillCardPanel().getHeadItem("pk_costproject").getValueObject();
			if(pk_costproject==null){
				MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "请先选择收费项目！");
				e.getBillCardPanel().setHeadItem(e.getKey(), "");
				e.getBillCardPanel().setHeadItem("pk_costproject", "");
				ref.getRefModel().addWherePart(" and pk_org='"+pk_org +"' and chargeitem=''");
				return ;
			}
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org +"' and chargeitem='"+pk_costproject.toString()+"'");
			ref.getRefModel().getRefSql();
		}
		
		
		
	}
	//字符串封装
		public String getStgObj(Object obj){
			return obj==null?"":obj.toString();
		}

}
