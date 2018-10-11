package nc.ui.zl.ld_report_cwdy.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
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
				ShowStatusBarMsgUtil.showStatusBarMsg("δ�鵽�κ���Ϣ��", getModel().getContext());
				return ;
			}
			
			hmodel.execLoadFormula();
		}
		
	  }
	  
		@SuppressWarnings("unchecked")
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
			
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String where1 = obj2.getWhere();
			String jieguo = "";
			String jieguo1 = "";
			String pk_account1 = "";
			String pk_account2 ="";
			String pk_account1_1 = "";
			String pk_account2_1 ="";
			 //��ȡ����������� ����ѯ��Ӧyearth
			if(where1.indexOf("AND report_cwdy.pk_account")!=-1){
				System.out.println(where1.substring(where1.indexOf("AND report_cwdy.pk_account")+4,where1.indexOf("AND report_cwdy.pk_account")+52));
				jieguo = where1.substring(where1.indexOf("AND report_cwdy.pk_account")+4,where1.indexOf("AND report_cwdy.pk_account")+52);
				jieguo1 = jieguo;
				System.out.println(jieguo.substring(27,jieguo.length()-1));
				pk_account1 = jieguo.substring(27,jieguo.length()-1);
				Object obj = null;
				if(jieguo.indexOf(">")!=-1||jieguo.indexOf("<")!=-1){
					 String sql1 = "select ba.yearmth from bd_accperiodmonth ba where ba.pk_accperiodmonth in('"+pk_account1+"') ";
					 try {
						obj=iQ.executeQuery(sql1, new ColumnProcessor());
					} catch (BusinessException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					 pk_account1_1 = obj.toString();
					 jieguo =  jieguo.replaceAll(pk_account1, pk_account1_1);
					 
				}
			}
			 if(where1.indexOf("(report_cwdy.pk_account")!=-1){//�ж��Ƿ������ȡ���ַ�
				 
		           jieguo = where1.substring(where1.indexOf("(report_cwdy.pk_account"),where1.indexOf("and report_cwdy.pk_account")+53);
		           jieguo1 = jieguo;
		           pk_account1 = jieguo.substring(28,48);
		           pk_account2 = jieguo.substring(81,101);
		           List<Object> objL = null;
		           
		        	   String sql1 = "select ba.yearmth from bd_accperiodmonth ba where ba.pk_accperiodmonth in('"+pk_account1+"','"+pk_account2+"') order by yearmth asc";
			           
			           try {
			        	   objL=(List<Object>)iQ.executeQuery(sql1, new ArrayListProcessor());
						} catch (BusinessException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
				        if(objL.size()>1){
				        	Object[] obj1 = (Object[])objL.get(0);
					        Object[] obj3 = (Object[])objL.get(1);
					        pk_account1_1 = obj1[0].toString();
							pk_account2_1 = obj3[0].toString();
							jieguo =  jieguo.replaceAll(pk_account1, pk_account1_1);
							jieguo =  jieguo.replaceAll(pk_account2, pk_account2_1);
					        jieguo = " \\" + jieguo.substring(0, jieguo.length()-1)+"\\" +jieguo.substring(jieguo.length()-1,jieguo.length());
				        }else{
				        	Object[] obj1 = (Object[])objL.get(0);
				        	pk_account1_1 = obj1[0].toString();
				        	jieguo =  jieguo.replaceAll(pk_account1, pk_account1_1);
							jieguo =  jieguo.replaceAll(pk_account2, pk_account2_1);
							jieguo =  jieguo.replaceAll(">|<", "");
				        	
				        }
		           
		      }
			String where2 = where1.replaceAll(jieguo1, jieguo);
			String where = where2.replaceAll("report_cwdy.", "m.");

			String sql = "select m.orgname,m.projectname,m.pk_costproject,m.pk_account,sum(m.realmoney) nrealmoney," +
					"sum(m.notaxmny) notaxmny,sum(m.taxmny) taxmny,m.rate from " +
					"(select g.pk_org,og.name orgname,g.pk_project,p.name projectname,gb.pk_costproject,ba.yearmth pk_account," +
					"sum(gb.nskmny) realmoney,sum(gb.nsknotaxmny) notaxmny,sum(gb.nsktaxmny) taxmny,gb.ntaxrate rate from zl_gather_b gb "
						+"join zl_gather g on (gb.pk_gather=g.pk_gather and nvl(gb.dr,0)=0 and nvl(g.dr,0)=0 and g.vbillstatus=1) "
						+"left join zl_project p on g.pk_project = p.pk_project "
						+"left join org_orgs og on g.pk_org = og.pk_org "
						+"left join bd_accperiodmonth ba on (g.dbilldate>=ba.begindate and g.dbilldate<=ba.enddate) "
						+"group by gb.ntaxrate, g.pk_org,og.name,g.pk_project,p.name,gb.pk_costproject,gb.caccountperiod,ba.yearmth) m where "+where+" group by " +
						"m.rate,m.orgname,m.projectname,m.pk_account,m.pk_costproject order by m.orgname,m.projectname,m.pk_account,m.pk_costproject";
		
			List<Map<String, Object>> listmap=null;
			
			try {
				listmap=(List<Map<String, Object>>)iQ.executeQuery(sql, new MapListProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			//���㵱ǰ��֯��ÿ����������շ���Ŀ����
			Map<Object, Integer> map_xj = new HashMap<Object, Integer>();
			if(listmap!=null&&listmap.size()>0){
				
				String j1 = "";
				String j2 = "";
				String j3 = "";
				int count = 0;
				for(Map<String, Object> map : listmap){
					j1 = getStrObj(map.get("pk_project")) + getStrObj(map.get("pk_account"));
					if(j1.equals(j2)&&j1!=null&&j2!=null){
						continue;
					}
					for(Map<String, Object> map1 : listmap){
						j3 = getStrObj(map1.get("pk_project")) + getStrObj(map1.get("pk_account"));
						if(j1.equals(j3)&&j1!=null&&j3!=null){
							count++;
						}
					}
					
					map_xj.put(j1, count);
					count=0;
					j2 = getStrObj(map.get("pk_project")) + getStrObj(map.get("pk_account"));
					
				}
			}
			
			
			if(listmap!=null&&listmap.size()>0){
				String up_value = "";//��һ����֯�µĻ����
				int count1 = 0;
				int count2 = 0;
				UFDouble nreal = new UFDouble(0);
				UFDouble notax = new UFDouble(0);
				UFDouble tax = new UFDouble(0);
				UFDouble nAllreal = new UFDouble(0);
				UFDouble nAllreal2 = new UFDouble(0);
				UFDouble nAllreal3 = new UFDouble(0);
				for(Map<String, Object> map : listmap){
					
					hmodel.addLine();
					for(String key : map.keySet()){
						hmodel.setValueAt(map.get(key), hmodel.getRowCount()-1, key);
					}
					if(hmodel.getRowCount()-1==0){
						hmodel.setValueAt(cond1, hmodel.getRowCount()-1, "pk_org");
					}

					count2++;
					
					
					//С��
					
					String t = getStrObj(map.get("pk_project")) + getStrObj(map.get("pk_account"));
					if((t.equals(getStrObj(up_value)))&&!t.equals("")){
						count1++;
						nreal = nreal.add(getUfdObj(map.get("nrealmoney")));
						notax = notax.add(getUfdObj(map.get("notaxmny")));
						tax = tax.add(getUfdObj(map.get("taxmny")));
						
					}else if(getStrObj(up_value).equals("")&&!t.equals("")){
						count1=1;
						nreal = nreal.add(getUfdObj(map.get("nrealmoney")));
						notax = notax.add(getUfdObj(map.get("notaxmny")));
						tax = tax.add(getUfdObj(map.get("taxmny")));
						up_value = t;
					}
					
					if(map_xj.get(t).equals(count1)&&count1!=0){
						hmodel.addLine();
						for(String key : map.keySet()){
							if(key.equals("orgname")){
								hmodel.setValueAt("����ڼ�С��", hmodel.getRowCount()-1, key);
							}else if(key.equals("nrealmoney")){
								hmodel.setValueAt(nreal, hmodel.getRowCount()-1, key);
							}else if(key.equals("notaxmny")){
								hmodel.setValueAt(notax, hmodel.getRowCount()-1, key);
							}else if(key.equals("taxmny")){
								hmodel.setValueAt(tax, hmodel.getRowCount()-1, key);
							}
							
						}
						//��ӱ���ɫ
						for(int i=4;i<11;i++){
							hmodel.setBackground(new Color(255, 238, 188), hmodel.getRowCount()-1, i);
						}
						nreal = new UFDouble(0);
						notax = new UFDouble(0);
						tax = new UFDouble(0);
						count1=0;
						up_value="";
						
					}
					/*if(map_xj.get(t).equals(count1)){
						nreal = new UFDouble(0);
						count1=0;
						up_value="";
					}*/
					//�ܼ�
					nAllreal = nAllreal.add(getUfdObj(map.get("nrealmoney")));
					nAllreal2 = nAllreal2.add(getUfdObj(map.get("notaxmny")));
					nAllreal3 = nAllreal3.add(getUfdObj(map.get("taxmny")));
					
					if(listmap.size()==count2){
						billForm.getBillCardPanel().setTatolRowShow(true);
						//���źϼ�
						hmodel.addLine();
						
						for(String key : map.keySet()){
							if(key.equals("pk_account")){
								hmodel.setValueAt("�ܼ�", hmodel.getRowCount()-1, key);
								
							}else if(key.equals("nrealmoney")){
								
								hmodel.setValueAt(getUfdObj(nAllreal), hmodel.getRowCount()-1, key);
							}else if(key.equals("notaxmny")){
								
								hmodel.setValueAt(getUfdObj(nAllreal2), hmodel.getRowCount()-1, key);
							}else if(key.equals("taxmny")){
								
								hmodel.setValueAt(getUfdObj(nAllreal3), hmodel.getRowCount()-1, key);
							}
						}
						for(int i=1;i<11;i++){
							hmodel.setBackground(new Color(255, 255, 188), hmodel.getRowCount()-1, i);
						}
						billForm.getBillCardPanel().setTatolRowShow(false);
					}
					
				}
			}
		}
		
		private String getStrObj(Object obj){
			return obj==null?"":obj.toString();
		}
		
		private UFDouble getUfdObj(Object obj){
			return obj==null?new UFDouble(0):new UFDouble(obj.toString());
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
				Logger.info("û���ҵ�" + col + "�ж�Ӧʵ����.");
				return -1;
			}

		
}
