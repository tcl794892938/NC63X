package nc.ui.zl.ly_report.ace.view;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 退租管理统计表查询
 * @author Liu
 *
 */
public class QueryActionTZGL extends DefaultQueryAction {
	private static final long serialVersionUID = 5953276677401427168L;

	private ShowUpableBillForm bill;

	public void doAction(ActionEvent e) throws Exception {

		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {

			BillModel bmodel = bill.getBillCardPanel().getBillModel();
			bmodel.clearBodyData();
			this.processQuery2();

			if (bmodel.getRowCount() <= 0) {
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return;
			}
			
			bmodel.execLoadFormula();
		}

	}

	@SuppressWarnings("unchecked")
	private void processQuery2() {
		try {
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();

			FromWhereSQLImpl obj2 = (FromWhereSQLImpl) queryScheme.getTableJoinFromWhereSQL();
			BillModel bmodel = bill.getBillCardPanel().getBillModel();

			String cond=queryScheme.get("description").toString();
			String cond1="";
			if(cond.startsWith("(")){
				cond1=cond.substring(1, cond.length()-1);
			}else{
				cond1=cond.substring(0, cond.length());
			}
			
			String where = obj2.getWhere();
			where=where.replaceAll("zl_zbzj.", "a.");
			where=where.replaceAll("a.htcode", "b.contractid");
			where=where.replaceAll("a.housename", "a.pk_house");
			where=where.replaceAll("a.tzdate", "b.dtzdate");
			where=where.replaceAll("a.htenddate", "b.denddate");
//			String sql = "select a.pk_org as 组织,a.pk_project as 项目信息,a.pk_customer as 客户名称,a.housename as 房产名称,a.htcode as 合同编号," +
//					"a.htenddate as 合同到期日,a.tzdate as 退租时间,a.pk_costproject as 科目名称,sum(a.ytmny) as 应退金额 , sum(a.tkmnydetail) as 退款金额明细  from " +
//					"(select t.pk_org,t.pk_project,tk.pk_customer,b.name||'-'||tk.roomnumber housename,t.contractid htcode,t.denddate htenddate," +
//					"t.dtzdate tzdate,r.pk_costproject,(case when sum(r.nrecmoney)<0 then abs(sum(r.nrecmoney)) else 0 end) ytmny, " +
//					"(case when sum(r.nrealmoney)<0 then abs(sum(r.nrealmoney)) else 0 end) tkmnydetail " +
//					"from zl_throwalease_khfc tk left join zl_throwalease t on t.pk_throwalease=tk.pk_throwalease and nvl(t.dr,0)=0 and " +
//					"t.vbillstatus=1 left join zl_buildingfile b on tk.pk_building=b.pk_buildingfile and nvl(b.dr,0)=0 left join zl_recbill r on " +
//					"t.pk_throwalease=r.vsrcid where nvl(tk.dr,0)=0 group by t.pk_org,t.pk_project,tk.pk_customer,b.name," +
//					"tk.roomnumber,t.contractid,t.denddate,t.dtzdate,r.pk_costproject having r.pk_costproject is not null) a where "+where+" group " +
//					"by a.pk_org,a.pk_project,a.pk_customer,a.housename,a.htcode,a.htenddate,a.tzdate,a.pk_costproject order by a.pk_project," +
//					"a.pk_customer,a.housename,a.htcode";
			
			String sql2="select a.pk_org,a.pk_project,a.pk_customer,a.pk_house,b.contractid,b.denddate,b.dtzdate,a.pk_costproject," +
					"abs(sum(a.nrecmoney)) as ytmny,abs(sum(a.nrealmoney)) as tkmnydetail " +
					" from zl_recbill a left join zl_throwalease b on a.vsrcid=b.pk_throwalease" +
					" where a.dr=0 and b.dr=0 and a.nrecmoney<0 and " +where+
					" group by a.pk_org,a.pk_project,a.pk_customer,a.pk_house,b.contractid,b.denddate,b.dtzdate,a.pk_costproject" +
					" order by a.pk_org,a.pk_project,a.pk_customer,a.pk_house,b.contractid";
			
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

			List<Map<String, Object>> clist = (List<Map<String, Object>>) iQ.executeQuery(sql2, new MapListProcessor());

			if (clist != null && clist.size() > 0) {
				for (int i = 0; i < clist.size(); i++) {
					bmodel.addLine();
					bmodel.setValueAt(clist.get(i).get("pk_org"), i, "pk_org");
					bmodel.setValueAt(clist.get(i).get("pk_project"), i, "pk_project");
					bmodel.setValueAt(clist.get(i).get("pk_customer"), i, "pk_customer");
					bmodel.setValueAt(clist.get(i).get("pk_house"), i, "housename");
					bmodel.setValueAt(clist.get(i).get("contractid"), i, "htcode");
					bmodel.setValueAt(clist.get(i).get("denddate"), i, "htenddate");
					bmodel.setValueAt(clist.get(i).get("dtzdate"), i, "tzdate");
					bmodel.setValueAt(clist.get(i).get("pk_costproject"), i, "pk_costproject");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("tkmnydetail")), i, "tkmnydetail");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("ytmny")), i, "ytmny");
				}
			}
			
			if(bmodel.getRowCount()>0){
				bmodel.setValueAt(cond1, 0, "condition");
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	private UFDouble getUfdObj(Object obj) {
		return obj == null ? new UFDouble(0) : new UFDouble(obj.toString());
	}

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}