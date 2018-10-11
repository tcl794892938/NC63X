package nc.ui.zl.ld_report_mxqd.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

@SuppressWarnings("restriction")
public class QueryAction extends nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction{
	  private static final long serialVersionUID = 5953276677401427168L;
	  
	  private ShowUpableBillListView billlist;
	  private ShowUpableBillForm billForm;
	  
	  public void doAction(ActionEvent e) throws Exception {
		  
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
			
			
			BillModel hmodel = billForm.getBillCardPanel().getBillModel();
			
			hmodel.clearBodyData();
			billForm.getBillCardPanel().setTatolRowShow(true);
			this.processQuery2();
			
			
			if(hmodel.getRowCount()<=0){
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return ;
			}
			hmodel.execLoadFormula();
			
			if(hmodel.getRowCount()>0){
				UFDouble yszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("nreceivemoney"));
				UFDouble sszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("ngetmoney"));
				UFDouble qfzj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("arrearage"));
				
				//总计
				hmodel.addLine();
				hmodel.setValueAt("总计", hmodel.getRowCount()-1, "pk_org");
				hmodel.setValueAt(yszj, hmodel.getRowCount()-1, "nreceivemoney");
				hmodel.setValueAt(sszj, hmodel.getRowCount()-1, "ngetmoney");
				hmodel.setValueAt(qfzj, hmodel.getRowCount()-1, "arrearage");
				//添加背景色
				for(int i=hmodel.getItemIndex("pk_org");i<16;i++){
					hmodel.setBackground(new Color(255, 255, 188), hmodel.getRowCount()-1, i);
				}
				billForm.getBillCardPanel().setTatolRowShow(false);
			}
			
		}
		
	  }
	  
		private void processQuery2() {
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
			
			FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
			BillModel hmodel = billForm.getBillCardPanel().getBillModel();
			String cond=queryScheme.get("description").toString();
			String cond1="";
			if(cond.startsWith("(")){
				cond1=cond.substring(1, cond.length()-1);
			}else{
				cond1=cond.substring(0, cond.length());
			}
			
			String where1 = obj2.getWhere();
			String where = where1.replaceAll("report_mxqd.", " ");
			String sql = "select m.pk_org,m.pk_project,m.pk_customer,m.buildname,m.floorn,m.unit,m.roomnumber,m.pk_house,m.buildarea," +
					"m.innerarea,m.pk_costproject, m.receivedate,max(m.nreceivemoney) nreceivemoney,max(m.ngetmoney) ngetmoney," +
					"max(m.nreceivemoney)-max(m.ngetmoney) arrearage," +
					"max(m.getdate) getdate,m.getman,m.paytype from (select r.pk_org,r.pk_project,h.pk_housesource pk_house," +
					"h.floorn,h.roomnumber,h.unit,h.buildarea, h.innerarea,h.buildname,r.pk_customer, r.pk_costproject, r.nrecmoney nreceivemoney," +
					"(case when r.gatherdate is null then r.begindate else r.gatherdate end) receivedate," +
					"(case when r.nrealmoney is null then 0 else r.nrealmoney end) ngetmoney,g.dbilldate getdate,g.creator getman," +
					"g.pk_paytype paytype from zl_recbill r left join zl_housesource h on (r.pk_house = h.pk_housesource and nvl(h.dr, 0) = 0) " +
					"left join zl_gather_b gb on (r.pk_recbill = gb.vsrcid  and r.pk_house is not null and nvl(gb.dr, 0) = 0) left join zl_gather g on " +
					"(gb.pk_gather = g.pk_gather and  g.vbillstatus = 1 and nvl(g.dr, 0) = 0) where nvl(r.dr, 0) = 0 and r.pk_house not in " +
					"(select h.pk_housesource from zl_housesource h where h.dr<>0) order by r.pk_org,r.pk_project,to_number(h.floorn) desc," +
					"to_number(h.unit) asc, to_number(h.roomnumber) asc, r.pk_customer) m  where  "+where+" group by m.pk_org,m.pk_project," +
							"m.floorn,m.unit,m.roomnumber,m.pk_customer,m.buildname,m.pk_house,m.buildarea,m.innerarea,m.pk_costproject," +
							"m.receivedate,m.getman,m.paytype";

			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			List<Map<String, Object>> listmap=null;
			
			try {
				listmap=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			
			if(listmap!=null&&listmap.size()>0){
				for(Map<String, Object> map : listmap){
					hmodel.addLine();
					for(String key : map.keySet()){
						hmodel.setValueAt(map.get(key), hmodel.getRowCount()-1, key);
					}
					if(hmodel.getRowCount()-1==0){
						hmodel.setValueAt(cond1, hmodel.getRowCount()-1,"pk_report_mxqd" );
					}
				}
			}
			
		}
		
		private String getStrObj(Object obj){
			return obj==null?"":obj.toString();
		}
		private String getStrIsObj(Object obj){
			return obj==null?"":obj.toString()+"、";
		}
		private Object getObj(Object str){
			return str==""?null:str;
		}
		
		private UFDouble getUfdObj(Object obj){
			return obj==null?new UFDouble(0):new UFDouble(obj.toString());
		}
		
		//日期截取
		private String getsubObj(Object obj){
			return obj==null?"":obj.toString().substring(0, 10);
		}

		public ShowUpableBillListView getBilllist() {
			return billlist;
		}

		public void setBilllist(ShowUpableBillListView billlist) {
			this.billlist = billlist;
		}
		
		 public ShowUpableBillForm getBillForm() {
			return billForm;
		}

		public void setBillForm(ShowUpableBillForm billForm) {
			this.billForm = billForm;
		}

		protected int getBodyColumnByCol(BillItem[] shows,int col) {
				int n = -1;
				for (int i = 0; i < shows.length; i++) {
					BillItem item = shows[i];
					if (item.isShow())
						n++;
					if (i == col)
						return n;
				}
				Logger.info("没有找到" + col + "列对应实际列.");
				return -1;
			}

		
}
