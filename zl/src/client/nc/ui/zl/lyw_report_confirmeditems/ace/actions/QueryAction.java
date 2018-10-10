package nc.ui.zl.lyw_report_confirmeditems.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFDouble;

public class QueryAction extends DefaultQueryAction{
	private static final long serialVersionUID = -178948818515512910L;
	private ShowUpableBillForm billForm;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
			BillModel hmodel= billForm.getBillCardPanel().getBillModel();
			hmodel.clearBodyData();
			billForm.getBillCardPanel().setTatolRowShow(true);
			this.processQuery2();
			if(hmodel.getRowCount()<=0){
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return ;
			}
			hmodel.execLoadFormula();
			if(hmodel.getRowCount()>0){
				
				UFDouble yszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("nnotaxmny"));
				//UFDouble sszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("amountconfirmed"));
				UFDouble dqtk=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("ntax"));
				
				Double num1=new BigDecimal(yszj.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//Double num2=new BigDecimal(sszj.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num3=new BigDecimal(dqtk.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				//billForm.getBillCardPanel().getTotalTableModel().setValueAt(num1, 0, hmodel.getItemIndex("nnotaxmny"));
				//billForm.getBillCardPanel().getTotalTableModel().setValueAt(num2, 0, hmodel.getItemIndex("amountconfirmed"));
				//billForm.getBillCardPanel().getTotalTableModel().setValueAt(num3, 0, hmodel.getItemIndex("ntax"));
				hmodel.addLine();
				hmodel.setValueAt(num1, hmodel.getRowCount()-1, "nnotaxmny");
				hmodel.setValueAt(num3, hmodel.getRowCount()-1, "ntax");
				hmodel.setValueAt("合计", hmodel.getRowCount()-1, "pk_customer");
				for(int i=2;i<9;i++){
					hmodel.setBackground(new Color(255, 255, 188), hmodel.getRowCount()-1, i);
				}
				billForm.getBillCardPanel().setTatolRowShow(false);
			}
		}
	}
	
	private void processQuery2() throws Exception {
		IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		BillModel hmodel = billForm.getBillCardPanel().getBillModel();
		//解析where条件
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String where2=where.replaceAll("confirmeditems","m");
		//将会计年月的pk替换为年月
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int index = where2.indexOf("caccountperiod >= '");//
		if (index >= 0){
			 String beginPk = where2.substring(index+"caccountperiod >= '".length(),index+"caccountperiod >= '".length()+"1001E4100000000C3PEE".length());
			 String sql = "select ba.yearmth from bd_accperiodmonth ba where ba.pk_accperiodmonth ='"+beginPk+"'";
			 Object beginVal=iQ.executeQuery(sql, new ColumnProcessor());
			 where2=where2.replaceFirst(beginPk, beginVal.toString());
		}
		int index2 = where2.indexOf("caccountperiod <= '");//
		if (index2 >= 0){
			 String endPk = where2.substring(index2+"caccountperiod <= '".length(),index2+"caccountperiod <= '".length()+"1001E4100000000C3PEE".length());
			 String sql = "select ba.yearmth from bd_accperiodmonth ba where ba.pk_accperiodmonth ='"+endPk+"'";
			 Object endVal=iQ.executeQuery(sql, new ColumnProcessor());
			 where2=where2.replaceFirst(endPk, endVal.toString());
		}
		where2=where2.replaceAll("m.caccountperiod", "yearmth");
		where2=where2.replaceAll("m.pk_project", "m.pp");
		where2=where2.replaceAll("m.chargingproject", "m.pk_costproject");
		System.out.println(where2);
		String where3=where2.replaceAll("pk_building", "buildno");
		where3=where3.replaceAll("m.houseproperty", "m.pk_house");
		//合同管理
		String sql = "select m.pk_org,m.pp,m.pk_project,m.dperiod,m.pk_house,m.buildno,m.pk_customer,m.pk_costproject,m.nrentarea,m.yearmth,sum(m.nnotaxmny) nnotaxmny,sum(m.amountconfirmed) amountconfirmed,sum(m.ntax) ntax " 
					+" from (select b.pk_org,b.pk_project pp,p.name  pk_project,db.caccountperiod dperiod,db.houseproperty pk_house,db.buildno,db.pk_customer,db.chargingproject pk_costproject,db.nrentarea,c.amountreceivable  nnotaxmny,nvl(sum(db.nnotaxmny),0) amountconfirmed,nvl(sum(db.ntaxmny),0) ntax,ba.yearmth,db.vsrcid  from zl_billconfirmedb db "
					+"join zl_billconfirmed b on(nvl(db.dr,0)=0 and nvl(b.dr,0)=0 and b.vbillstatus=1 and db.pk_billconfirmed=b.pk_billconfirmed) "
					+"join bd_accperiodmonth ba on (db.caccountperiod=ba.pk_accperiodmonth) "
					+"join zl_project p on (p.pk_project=b.pk_project) "
					+"join zl_confirmation c on(c.pk_confirmation=db.vsrcid) "
					+"group by b.pk_org,b.pk_project,p.name,db.caccountperiod,db.houseproperty,db.pk_customer,db.nrentarea,c.amountreceivable,db.vsrcid,ba.yearmth,db.chargingproject,db.buildno "
					+"order by b.pk_org,b.pk_project,db.caccountperiod) m where "+where3
					+" and pk_costproject not in ('1001E4100000000C3YH2','1001E4100000000C3YH3') "
					+" group by m.pk_org,m.pp,m.pk_project,m.dperiod,m.pk_house,m.buildno,m.pk_customer,m.pk_costproject,m.nrentarea,m.yearmth order by m.pk_org,m.pp,m.dperiod";
		@SuppressWarnings("unchecked")
		List<Map<String , Object>> listmap=(List<Map<String , Object>>)iQ.executeQuery(sql, new MapListProcessor());
		
		//计算每个会计月的记录个数
		Map<Object, Integer> map_xj = new HashMap<Object, Integer>();
		if(listmap!=null&&listmap.size()>0){
			String j1 = "";
			String j2 = "";
			String j3 = "";
			int count = 0;
			for(Map<String, Object> map1 : listmap){
				j1 = getStrObj(map1.get("pp")) + getStrObj(map1.get("dperiod"));
				if(j1.equals(j3)&&j1!=null&&j3!=null){
					continue;
				}
				for(Map<String, Object> map2 : listmap){
					j2 = getStrObj(map2.get("pp")) + getStrObj(map2.get("dperiod"));
					if(j1.equals(j2)&&j1!=null&&j2!=null){
						count++;
					}
				}
				map_xj.put(j1, count);
				count=0;
				j3 = getStrObj(map1.get("pp")) + getStrObj(map1.get("dperiod"));
			}
		}
		
		if(listmap!=null&&listmap.size()>0){
			String up_value = "";//上一行项目下的会计月
			int count1 = 0;
			UFDouble my1 = new UFDouble(0);
			UFDouble my2 = new UFDouble(0);
			UFDouble my3 = new UFDouble(0);
			
			for(Map<String, Object> map : listmap){
				
				hmodel.addLine();
				for(String key : map.keySet()){
					hmodel.setValueAt(map.get(key), hmodel.getRowCount()-1, key);
				}
				if(hmodel.getRowCount()-1==0){
					hmodel.setValueAt(where, 0, "condition");
				}
				String t = getStrObj(map.get("pp")) + getStrObj(map.get("dperiod"));
				if((t.equals(getStrObj(up_value)))&&!t.equals("")){
					count1++;
					my1 = my1.add(getUfdObj(map.get("nnotaxmny")));
					my2 = my2.add(getUfdObj(map.get("amountconfirmed")));
					my3 = my3.add(getUfdObj(map.get("ntax")));
					
				}else if(getStrObj(up_value).equals("")&&!t.equals("")){
					count1=1;
					my1 = my1.add(getUfdObj(map.get("nnotaxmny")));
					my2 = my2.add(getUfdObj(map.get("amountconfirmed")));
					my3 = my3.add(getUfdObj(map.get("ntax")));
					up_value = t;
				}
				
				if(map_xj.get(t).equals(count1)&&count1!=0){
					hmodel.addLine();
					for(String key : map.keySet()){
						if(key.equals("pk_project")){
							hmodel.setValueAt("会计年月小计", hmodel.getRowCount()-1, key);
						}else if(key.equals("nnotaxmny")){
							hmodel.setValueAt(my1, hmodel.getRowCount()-1, key);
						}else if(key.equals("amountconfirmed")){
							hmodel.setValueAt(my2, hmodel.getRowCount()-1, key);
						}else if(key.equals("ntax")){
							hmodel.setValueAt(my3, hmodel.getRowCount()-1, key);
						}
						
					}
					//添加背景色
					for(int i=2;i<9;i++){
						hmodel.setBackground(new Color(255, 238, 188), hmodel.getRowCount()-1, i);
					}
					my1 = new UFDouble(0);
					my2 = new UFDouble(0);
					my3 = new UFDouble(0);
					count1=0;
					up_value="";
					
				}
				
			}
		}
		
		/*String sql_all =
				"select c.houseproperty,c.pk_customer,c.chargingproject,c.nrentarea,c.caccountperiod,c.nnotaxmny,c.ntaxmny,ba.yearmth"
				+" from zl_confirmation c join bd_accperiodmonth ba on (c.caccountperiod=ba.pk_accperiodmonth)"
				+" where c.dr=0 and c.vbillstatus='1' and "+where3;
		@SuppressWarnings("unchecked")
		List<Map<String , Object>> map_alls=(List<Map<String , Object>>)iQ.executeQuery(sql_all, new MapListProcessor());
		BillModel bm = billForm.getBillCardPanel().getBillModel();
		for(int i=0;i<map_alls.size();i++){
			Map<String,Object> map_all=map_alls.get(i);
			bm.addLine();
			bm.setValueAt(where, 0, "condition");
			bm.setValueAt(map_all.get("houseproperty"), i, "pk_house");
			bm.setValueAt(map_all.get("pk_customer"), i, "pk_customer");
			bm.setValueAt(map_all.get("chargingproject"), i, "pk_costproject");
			bm.setValueAt(map_all.get("nrentarea"), i, "nrentarea");
			bm.setValueAt(map_all.get("caccountperiod"), i, "dperiod");
			bm.setValueAt(map_all.get("nnotaxmny"), i, "nnotaxmny");
			bm.setValueAt(map_all.get("ntaxmny"), i, "ntax");
		}*/
	}
	private String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
	private UFDouble getUfdObj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}