package nc.ui.zl.ly_pocustomers.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;

public class AceCardBodyBeforeEditHandler implements
		IAppEventHandler<CardBodyBeforeEditEvent> {

	private BillManageModel model;
	private BillForm billform;

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public BillForm getBillform() {
		return billform;
	}

	public void setBillform(BillForm billform) {
		this.billform = billform;
	}

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
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return;
		}

		if (e.getKey().equals("building")) {
			UIRefPane ref = (UIRefPane) e.getBillCardPanel()
					.getBodyItem(e.getKey()).getComponent();
			String pk_pro=getPKpro();
			ref.getRefModel().addWherePart(" and pk_projectid in ("+pk_pro+")");
			return;
		}
		
		if(e.getKey().equals("house")){
			UIRefPane ref = (UIRefPane) e.getBillCardPanel()
					.getBodyItem(e.getKey()).getComponent();
			Object building=e.getBillCardPanel().getBodyValueAt(e.getRow(), "building");
			ref.getRefModel().addWherePart(" and buildname='"+building+"'");
			return;
		}

	}

	// 获取所选择的项目主键
	public String getPKpro() {
		String pk_pro = "";
		try {
			Object rows = billform.getBillCardPanel()
					.getBillTable("id_pocustomers_org").getRowCount();
			Integer rowcount = (Integer) (rows == null ? "" : rows);
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String procodes = "";
			for (int i = 0; i < rowcount; i++) {
				Object procode = billform.getBillCardPanel().getBillModel("id_pocustomers_org").getValueObjectAt(i, "procode");
				procodes += "'" + getStgObj(procode) + "'";
				if (i < rowcount - 1) {
					procodes += ",";
				}
			}
			String sql = "select pk_project from zl_project where code in ("
					+ procodes + ")";
			@SuppressWarnings("unchecked")
			List<Object> pk_pros = (List<Object>) iQ.executeQuery(sql,
					new ArrayListProcessor());
			for (int j = 0; j < pk_pros.size(); j++) {
				Object[] pk=(Object[]) pk_pros.get(j);
				pk_pro += "'" + pk[0] + "'";
				if (j < pk_pros.size() - 1) {
					pk_pro += ",";
				}
			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		return pk_pro;
	}

	// 字符串封装
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

}
