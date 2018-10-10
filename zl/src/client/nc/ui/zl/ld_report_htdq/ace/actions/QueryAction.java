package nc.ui.zl.ld_report_htdq.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			this.processQuery2();
			
			
			if(hmodel.getRowCount()<=0){
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return ;
			}
			
			hmodel.execLoadFormula();
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
			String where2 = where1.replaceAll("report_htdq.", " ");
			String where = where2.replaceAll("cont.pk_contracttype", "pk_contracttype");
			 
			String newwhere1 = where.replaceAll("endt", "endtime");
			String []s=newwhere1.substring(1, newwhere1.length()-1).split("AND");
			String ht=" 1=1 ";
			String newwhere2=newwhere1;
			String house=" 2=2 ";
			for(int i=0;i<s.length;i++){
				if(s[i].contains("htstatus")){
					newwhere2=newwhere1.replace(s[i], " 1=1 ");
					ht=s[i];
					continue;
				}
				if(s[i].contains("pk_house")){
					newwhere2=newwhere1.replace(s[i], " 2=2");
					house=s[i];
					continue;
				}
			}
			String sql = "select * from (select * from (select t.pk_org,t.pk_project,t.pk_customer,t.pk_contracttype,h.estatename pk_house,t.htcode pk_code,t.denddate endtime,(case t.htstatus when 1 then '定租' when 2 then '签租' when 3 then '进场' when 4 then '退租' end) htstatus from zl_contract t "
							+"left join zl_contract_house th on (th.pk_contract=t.pk_contract and nvl(th.dr,0)=0) "
							+"left join zl_housesource h on(th.pk_house=h.pk_housesource and nvl(h.dr,0) = 0)"
							+"where nvl(t.dr,0)=0 and t.version=-1 and t.vbillstatus=1 and htcode is not null and +"+ht+"and"+house+") a where "+newwhere2
							+" union "
							+"select * from (select md.pk_org,md.pk_project,md.pk_customer,md.pk_contracttype,'' pk_house,md.code pk_code,md.enddate endtime,'' htstatus from zl_mdcontract md "
							+"where nvl(md.dr,0)=0 and md.version=-1 and md.state=1) b where "+newwhere1
							+" union "
							+"select * from (select p.pk_org,p.pk_project,p.pk_customer,p.pk_contracttype,h.estatename pk_house,p.code pk_code,p.enddate endtime,'' htstatus from zl_parkcontract p "
							+"join zl_parkcontract_b pb on(p.pk_parkcontract=pb.pk_parkcontract and nvl(p.dr,0)=0 and nvl(pb.dr,0)=0 and p.version=-1 and p.vbillstatus=1)"
							+"left join zl_housesource h on(pb.parknum=h.pk_housesource and nvl(h.dr,0) = 0)) c where "+newwhere1
							
							+" union "
							+"select * from (select c.pk_org,c.pk_project,c.pk_customer,c.pk_contracttype,'' pk_house,c.code pk_code,c.enddate endtime,'' htstatus "
							+"from zl_carcontract c where nvl(c.dr,0)=0 and c.version=-1 and c.vbillstatus=1) d where "+newwhere1
							+") me order by me.pk_org,me.pk_project,me.endtime asc";
			
			
			
			
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			List<Map<String, Object>> listmap=null;
		//	List<Map<String, Object>> listmap1 = new ArrayList<Map<String,Object>>();
			List<Integer> intList = new ArrayList<Integer>();
			
			try {
				
				listmap=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			

			
			if(listmap!=null&&listmap.size()>0){
				Map<String, Object> map = null;
				String m1 = "";
				String m2 = "";
				for(int i=0;i<listmap.size();i++){
					m1 = getStrObj(listmap.get(i).get("pk_org")) + getStrObj(listmap.get(i).get("pk_customer")) +getStrObj(listmap.get(i).get("pk_contracttype"))+getStrObj(listmap.get(i).get("pk_code"))+getStrObj(listmap.get(i).get("endtime"));
					for(int j=i+1;j<listmap.size();j++){
						m2 =  getStrObj(listmap.get(j).get("pk_org")) + getStrObj(listmap.get(j).get("pk_customer"))+getStrObj(listmap.get(j).get("pk_contracttype"))+getStrObj(listmap.get(j).get("pk_code"))+getStrObj(listmap.get(j).get("endtime"));
						if(intList.contains(i)){
							continue;
						}
						if(m1.equals(m2)&&!m1.equals("")&&listmap.get(i).get("pk_house")!=null&&listmap.get(j).get("pk_house")!=null&&!listmap.get(i).get("pk_house").equals(listmap.get(j).get("pk_house"))){
							/*map = new HashMap<String, Object>();
							map = listmap.get(i);
							map.put("pk_house", getStrIsObj(listmap.get(i).get("pk_house"))+getStrObj(listmap.get(j).get("pk_house")));
							listmap1.remove(listmap.get(i));
							listmap1.remove(listmap.get(j));
							listmap1.add(map);
							count++;*/
							String m = getStrIsObj(listmap.get(i).get("pk_house"))+getStrObj(listmap.get(j).get("pk_house"));
							listmap.get(i).put("pk_house", m);
							listmap.get(j).put("pk_house", m);
							intList.add(j);
						}
					}
				}
			}
			if(listmap!=null&&listmap.size()>0){
				int count=0;
				for(int i:intList){
					listmap.remove(i-count);
					count++;
				}
			}
			if(listmap!=null&&listmap.size()>0){
				
				for(int i=0;i<listmap.size();i++){
					hmodel.addLine();
					for(String key : listmap.get(i).keySet()){
						hmodel.setValueAt(listmap.get(i).get(key), hmodel.getRowCount()-1, key);
					}
					if(hmodel.getRowCount()-1==0){
						hmodel.setValueAt(cond1, hmodel.getRowCount()-1,"pk_org1" );
					}

				}
				/*for(Map<String, Object> map1 : listmap){
					hmodel.addLine();
					for(String key : map1.keySet()){
						hmodel.setValueAt(map1.get(key), hmodel.getRowCount()-1, key);
					}
				}*/
			}
			
			/*String sql = "select m.pk_org,m.pk_org1,m.pk_project,m.pk_customer,m.pk_building,m.pk_house1,concat(m.name1,substr(m.estatename,instr(m.estatename,'#')+1)) pk_house,m.pk_code,m.endtime,m.vsrcid,m.projectname,m.customname,m.pk_contracttypen,cont.name pk_contracttype "    
						 +"from (select r.pk_org pk_org,g.name pk_org1, r.pk_project pk_project,r.pk_customer pk_customer,r.pk_building pk_building,r.pk_house pk_house1,h.estatename estatename,bu.name name1,concat(concat(ct.denddate,md.enddate),concat(cat.enddate,pt.enddate)) endtime,"
						 +"concat(concat(ct.htcode,md.code),concat(cat.code,pt.code)) pk_code,max(r.enddate) da,r.vsrcid vsrcid,p.name projectname,cu.customername customname,concat(concat(cat.pk_contracttype,pt.pk_contracttype),concat(ct.pk_contracttype,md.pk_contracttype)) pk_contracttypen "
						 +"from zl_recbill r left join zl_housesource h on(r.pk_house=h.pk_housesource) "
						 +"left join zl_buildingfile bu on (bu.pk_buildingfile=r.pk_building) "
						 +"left join zl_contract ct on (r.vsrcid=ct.pk_contract) "
						 +"left join zl_mdcontract md on (r.vsrcid=md.pk_mdcontract) "
						 +"left join zl_carcontract cat on (r.vsrcid=cat.pk_carcontract) "
						 +"left join org_orgs g on r.pk_org = g.pk_org "
						 +"left join zl_project p on r.pk_project = p.pk_project "
						 +"left join zl_customer cu on r.pk_customer = cu.pk_customer "
						 +"left join zl_parkcontract pt on (r.vsrcid=pt.pk_parkcontract) where nvl(r.dr,0)=0 "
						 +"group by r.pk_org,r.pk_project,r.pk_customer,ct.pk_contracttype,md.pk_contracttype,cat.pk_contracttype,pt.pk_contracttype,pk_house,estatename,bu.name,ct.denddate,md.enddate,cat.enddate,pt.enddate,ct.htcode,md.code,cat.code,pt.code,r.vsrcid,r.pk_building,g.name,p.name,cu.customername"
						 +") m,zl_contracttype cont where m.pk_contracttypen=cont.pk_contracttype and m.pk_code is not null "+"and "+newwhere1+"order by m.pk_org,m.pk_project,m.customname,cont.pk_contracttype";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			List<Map<String, Object>> listmap=null;
			List<Map<String, Object>> listm = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapm = new HashMap<String, Object>();
			try {
				listmap=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if(listmap!=null&&listmap.size()>0){
				Object m1 = null;
				Object m1_1 = null;
				Object m1_2 = null;
				Object m2 = null;
				Object m2_1 = null;
				Object m2_2 = null;
				Object m2_3 = null;
				Object m2_4 = null;
				int j=0;
				for(int i=0;i<listmap.size();i++){
					m1 = listmap.get(i).get("pk_project");
					m1_1 = listmap.get(i).get("pk_customer");
					m1_2 = listmap.get(i).get("pk_code");
					if(m1!=null&&m2!=null&&m1.equals(m2)&&m1_1.equals(m2_1)&&(m1_2.equals(m2_3))){
						mapm = listmap.get(i-1);
						mapm.put("pk_house", getStrIsObj(m2_2)+getStrObj(listmap.get(i).get("pk_house")));
						
						listm.remove(listmap.get(i-1));
						listm.add(mapm);
						j=j-1;
					}else{
						mapm = listmap.get(i);
						mapm.put("pk_house", getStrObj(mapm.get("pk_house")));
						
						listm.add(j, mapm);
					}
					m2 = listmap.get(i).get("pk_project");
					m2_1 = getObj(listmap.get(i).get("pk_customer"));
					m2_2 = getObj(listmap.get(i).get("pk_house"));
					m2_3 = getObj(listmap.get(i).get("pk_code"));
					m2_4 = getObj(getsubObj(listmap.get(i).get("endtime")));
					j++;
				}

			}
			
				
		if(listm!=null&&listm.size()>0){
			for(Map<String, Object> map : listm){
				hmodel.addLine();
				for(String key : map.keySet()){
					hmodel.setValueAt(map.get(key), hmodel.getRowCount()-1, key);
				}
			}
		}*/
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
