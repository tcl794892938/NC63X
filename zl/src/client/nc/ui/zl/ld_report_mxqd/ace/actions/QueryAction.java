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
			String sql = "select m.pk_org,m.pk_project,  m.pk_customer,m.buildname,m.floorn,m.unit,m.roomnumber,m.pk_house,m.buildarea,m.innerarea,m.pk_costproject, m.receivedate,m.nreceivemoney, "
						+"(case when m.nreceivemoney < 0 then -max(abs(m.ngetmoney1)) when m.nreceivemoney > 0 then  max(m.ngetmoney1) else 0 end) ngetmoney, "
						+"max(m.getdate1) getdate, m.arrearage, m.getman, m.paytype,m.pk_report_mxqd from (select r.pk_org,r.pk_project,h.pk_housesource pk_house,h.floorn,h.roomnumber,h.unit, "
						+"h.buildarea, h.innerarea,h.buildname,r.pk_customer, r.pk_costproject, r.nrecmoney nreceivemoney, "
						+"(case when r.gatherdate is null then r.begindate else r.gatherdate end) receivedate, "
						+"(case when gb.nysmny is not null then gb.nysmny + gb.nskmny else 0 end) ngetmoney1, "
						+"g.creationtime getdate1,(case when r.nrealmoney is not null then (r.nrecmoney - r.nrealmoney) else r.nrecmoney end) arrearage, "
						+"g.creator getman,g.pk_paytype paytype ,r.vbillcode pk_report_mxqd "
						+"from zl_recbill r left join zl_housesource h on  (r.pk_house = h.pk_housesource and nvl(h.dr, 0) = 0) "
						+"left join zl_gather_b gb on (r.pk_recbill = gb.vsrcid  and r.pk_house is not null and nvl(gb.dr, 0) = 0) "
						+"left join zl_gather g on (gb.pk_gather = g.pk_gather and  g.vbillstatus = 1 and nvl(g.dr, 0) = 0) where nvl(r.dr, 0) = 0 and r.pk_house not in (select h.pk_housesource from zl_housesource h where h.dr<>0) "
						+"order by r.pk_org, r.pk_project, to_number(h.floorn) desc, to_number(h.unit) asc, to_number(h.roomnumber) asc, r.pk_customer) m  where  "+where
						+" group by m.pk_org,m.pk_project,m.floorn,m.unit,m.roomnumber,  m.pk_customer,m.buildname,m.pk_house,m.buildarea,m.innerarea,m.pk_costproject,m.nreceivemoney, m.receivedate, "
						+" m.arrearage, m.getman, m.paytype,pk_report_mxqd "
						+"union "
						+"select * from (select g.pk_org,g.pk_project,g.pk_customer,h.buildname,h.floorn,h.unit,h.roomnumber,h.pk_housesource pk_house,h.buildarea,h.innerarea,gb.pk_costproject,gb.vdef5 receivedate,gb.nskmny nreceivemoney,"
						+"gb.nskmny ngetmoney,gb.vdef5 getdate,0 arrearage,g.creator getman,g.pk_paytype paytype, gb.vsrcid pk_report_mxqd from zl_gather_b gb "
						+"join zl_gather g on(g.pk_gather=gb.pk_gather and gb.vsrcid is null and nvl(gb.dr,0)=0 and nvl(g.dr,0)=0 and g.vbillstatus = 1) "
						+"left join zl_housesource h on(h.pk_housesource=gb.pk_house) where nvl(h.dr, 0) = 0)b where "+where
						+" order by pk_org,pk_project, pk_customer,buildname,pk_house,pk_costproject,receivedate";

			/*String sql = "select m.pk_org pk_org1,m.pk_project pk_project1, m.floorn,m.unit,m.roomnumber,m.pk_customer pk_customer1,m.orgname pk_org,m.projectname pk_project,m.buildname,m.estatename "
						+"pk_house,m.buildarea,m.innerarea,m.customername pk_customer,m.costname pk_costproject,m.nreceivemoney,m.receivedate,(case when m.nreceivemoney<0 then -max(abs(m.ngetmoney1)) " 
						+"when m.nreceivemoney>0 then max(m.ngetmoney1) else 0 end) ngetmoney,max(m.getdate1) getdate,m.arrearage,m.getman,m.paytype from ("
					    +"select r.pk_org,og.name orgname,r.pk_project,p.name projectname,r.pk_house,h.estatename,h.floorn,h.roomnumber,h.unit,h.buildarea,h.innerarea,h.buildname,r.pk_customer,cu.customername,r.pk_costproject, "
						+"ct.name costname,r.nrecmoney nreceivemoney,(case when r.gatherdate is null then r.begindate else r.gatherdate end) receivedate,(case when gb.nysmny is not null then gb.nysmny+gb.nskmny else 0 end)  ngetmoney1,g.creationtime getdate1, "
						+"(case when r.nrealmoney is not null then (r.nrecmoney - r.nrealmoney) else r.nrecmoney end) arrearage, "
						+"su.user_name getman,bb.name paytype from zl_recbill r "
						+"join zl_housesource h on (nvl(r.dr,0)=0 and r.pk_house is not null and r.pk_house=h.pk_housesource and nvl(h.dr,0)=0) "
						+"left join org_orgs og on r.pk_org = og.pk_org "
						+"left join zl_project p on r.pk_project = p.pk_project "
						+"left join zl_customer cu on r.pk_customer = cu.pk_customer "
						+"left join zl_costproject ct on r.pk_costproject=ct.pk_costproject "
						+"left join zl_gather_b gb on ( r.pk_recbill=gb.vsrcid and r.pk_house=gb.pk_house and gb.pk_house is not null and  r.pk_house is not null and nvl(gb.dr,0)=0) "
						+"left join zl_gather g on (gb.pk_gather=g.pk_gather and gb.pk_house is not null and g.vbillstatus = 1 and nvl(g.dr, 0) = 0 ) "
						+"left join sm_user su on (su.cuserid=g.creator)  "
						+"left join bd_balatype bb on(g.pk_paytype=bb.pk_balatype) "
						+"order by  r.pk_org,r.pk_project,to_number(h.floorn) desc,to_number(h.unit) asc,to_number(h.roomnumber) asc,r.pk_customer) m where "+ where+" "
						+"group by m.orgname ,m.projectname ,m.estatename ,m.buildarea,m.innerarea,m.customername,m.costname , m.nreceivemoney, m.receivedate, m.arrearage,m.buildname,"
						+"m.getman,m.paytype,m.pk_org,m.pk_project,m.floorn,m.unit,m.roomnumber,m.pk_customer order by m.pk_org,"
						+"m.pk_project,m.buildname,to_number(m.floorn) desc,to_number(m.unit) asc,to_number(m.roomnumber) asc,m.pk_customer";*/
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
