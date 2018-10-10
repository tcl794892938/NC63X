package nc.ui.zl.lyw_report_houseitems.ace.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
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
				UFDouble zlmj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("rentarea"));
				UFDouble czmj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("certificatearea"));
				UFDouble yszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("nreccollecttotal"));
				UFDouble sszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("ncollecttotal"));
				UFDouble qfzj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("narrearagetotal"));
				Double num1=new BigDecimal(yszj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num2=new BigDecimal(sszj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num4=new BigDecimal(qfzj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num5=new BigDecimal(zlmj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num6=new BigDecimal(czmj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				//总计
				hmodel.addLine();
				hmodel.setValueAt("总计", hmodel.getRowCount()-1, "pk_org");
				hmodel.setValueAt(num5, hmodel.getRowCount()-1, "rentarea");
				hmodel.setValueAt(num6, hmodel.getRowCount()-1, "certificatearea");
				hmodel.setValueAt(num1, hmodel.getRowCount()-1, "nreccollecttotal");
				hmodel.setValueAt(num2, hmodel.getRowCount()-1, "ncollecttotal");
				hmodel.setValueAt(num4, hmodel.getRowCount()-1, "narrearagetotal");
				//添加背景色
				for(int i=hmodel.getItemIndex("pk_org");i<16;i++){
					hmodel.setBackground(new Color(255, 255, 188), hmodel.getRowCount()-1, i);
				}
				billForm.getBillCardPanel().setTatolRowShow(false);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processQuery2() throws Exception {
		IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		//解析where条件
		String where=queryScheme.getTableListFromWhereSQL().getWhere();
		String cond=queryScheme.get("description").toString();
		String cond1="";
		if(cond.startsWith("(")){
			cond1=cond.substring(1, cond.length()-1);
		}else{
			cond1=cond.substring(0, cond.length());
		}
		
		String jie="";
		String jie1="";
		String jie3="1=1";
		
		//一个应收日期
		if(where.indexOf("AND houseitems.getday")!=-1){
			System.out.println(where.substring(where.indexOf("AND houseitems.getday")+4,where.indexOf("AND houseitems.getday")+46));
			jie = where.substring(where.indexOf("AND houseitems.getday")+4,where.indexOf("AND houseitems.getday")+46);
			jie1 = jie;
			jie = jie.replaceAll("houseitems.getday", "gatherdate");
			where = where.replaceAll("AND "+jie1, "");
		}else if(where.indexOf("(houseitems.getday")!=-1){//2个应收日期
			System.out.println(where.substring(where.indexOf("(houseitems.getday"),where.indexOf("and houseitems.getday")+47));
			jie = where.substring(where.indexOf("(houseitems.getday"),where.indexOf("and houseitems.getday")+47);
			jie1 = " \\" +jie.substring(0, jie.length()-1)+"\\" +jie.substring(jie.length()-1,jie.length());
			
			jie = jie.replaceAll("houseitems.getday", "gatherdate");
			where = where.replaceAll("AND"+jie1, "");
		}
		if(!jie.equals("")){
			jie3 = jie;
		}
		
		String where2 = where.replaceAll("houseitems.","m.");
		String where3 = where2.replaceAll("pk_org","pk_orgpk");
		String where1 = where3.replaceAll("pk_project","prject_pk");
		
		String sql="select * from (select x.pk_orgpk,x.pk_org,x.prject_pk,x.pk_project,x.pk_buildno,x.pk_house,x.pk_housesource,x.rentarea,x.certificatearea," +
				"x.housestate,x.pk_customer,sum(x.nreccollecttotal) nreccollecttotal,sum(x.ncollecttotal) ncollecttotal," +
				"sum(x.nreccollecttotal)-sum(x.ncollecttotal) narrearagetotal from " +
				"(select a.pk_orgpk,a.pk_org,a.prject_pk,a.pk_project,a.pk_buildno,a.floorn,a.unit,a.roomnumber,a.pk_house,a.pk_housesource," +
				"a.rentarea,a.certificatearea,a.housestate,s.pk_customer,(case when s.ys is null then 0 else s.ys end) nreccollecttotal," +
				"(case when s.ss is null then 0 else s.ss end) ncollecttotal from " +
				"(select o.pk_org pk_orgpk,o.name pk_org,p.pk_project prject_pk,p.name pk_project,h.buildname pk_buildno,h.floorn,h.unit," +
				"h.roomnumber,h.pk_housesource pk_house,h.pk_housesource,h.buildarea rentarea,h.innerarea certificatearea,h.housestate " +
				"from org_orgs o left join zl_project p on o.pk_org=p.pk_org left join zl_housesource h on p.pk_project=h.projectname " +
				"where nvl(o.dr,0)=0 and nvl(p.dr,0)=0 and nvl(h.dr,0)=0 and h.pk_housesource is not null) a left join " +
				"(select r.pk_customer,r.pk_house,r.nrecmoney ys,r.nrealmoney ss,r.gatherdate from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vbillstatus=1 and "+jie3+") s on a.pk_housesource=s.pk_house) x" +
				" group by  x.pk_orgpk,x.pk_org,x.prject_pk,x.pk_project,x.pk_buildno,x.floorn,x.unit,x.roomnumber,x.pk_house,x.pk_housesource,x.rentarea,x.certificatearea," +
				"x.housestate,x.pk_customer order by x.pk_orgpk,x.prject_pk,x.pk_buildno,floorn desc,unit asc,roomnumber asc) m where "+where1;
		
		/*String sql="select * from(select  og.name pk_org,h.pk_org pk_orgpk,h.projectname prject_pk,zp.name pk_project,h.buildname pk_buildno,h.pk_housesource pk_house,h.pk_housesource,h.floorn,h.unit,h.roomnumber,h.buildarea rentarea,h.innerarea certificatearea,b1.pk_customer,h.housestate,"
                +"nvl(b1.nreccollecttotal,0) nreccollecttotal,nvl(b2.ncollecttotal,0) ncollecttotal,nvl(b2.nrefundtotal,0) nrefundtotal," 
                +"  (nvl(b1.nreccollecttotal,0)-nvl(b2.ncollecttotal,0)- nvl( b2.nrefundtotal,0)) narrearagetotal from zl_housesource h "
				+"left join (select a4.pk_house,a4.pk_customer,sum(a4.nreccollecttotal) nreccollecttotal from (select * from(select aa.pk_house,aa.pk_customer,sum(nreccollecttotal) nreccollecttotal,aa.getday from(select h.pk_housesource pk_house,cu.pk_customer,(sum(nvl(cy.nysmny,0)) ) nreccollecttotal,cy.drecdate getday  from zl_housesource  h "
				+"join zl_contract_house ch on(h.pk_housesource =ch.pk_house and nvl(ch.dr, 0) = 0 and nvl(h.dr,0) = 0) "
				+"join zl_contract_cust cu on(ch.pk_contract=cu.pk_contract and nvl(cu.dr,0) = 0) "
				+"join zl_contract_ywcf cy on(ch.pk_contract=cy.pk_contract and nvl(cy.dr,0) = 0 and ch.pk_house=cy.pk_house) "
				+"join zl_contract c on(ch.pk_contract=c.pk_contract and nvl(c.dr,0) = 0 and c.vbillstatus=1 and c.version=-1) "
				+"group by h.pk_housesource,cu.pk_customer,cy.drecdate " 
				+"union all select h.pk_housesource pk_house,cu.pk_customer,(sum(nvl(cz.nysmny,0)) ) nreccollecttotal,cz.drecdate getday  from zl_housesource  h " 
				+"join zl_contract_house ch on(h.pk_housesource =ch.pk_house and nvl(ch.dr, 0) = 0 and nvl(h.dr,0) = 0) "
				+"join zl_contract_cust cu on(ch.pk_contract=cu.pk_contract and nvl(cu.dr,0) = 0) "
				+"join zl_contract c on(ch.pk_contract=c.pk_contract and nvl(c.dr,0) = 0 and c.vbillstatus=1 and c.version=-1) "
				+"join zl_contract_zqfycf cz on(ch.pk_contract=cz.pk_contract and nvl(cz.dr,0) = 0 and ch.pk_house=cz.pk_house) group by h.pk_housesource,cu.pk_customer,cz.drecdate )aa "+jie3+" group by aa.pk_house,aa.pk_customer,aa.getday ) a1 "
				+"union all "
				+"select * from(select h.pk_housesource pk_house,pb.pk_customer,(sum(case when pc.nreceivemoney is null then 0 else pc.nreceivemoney end)) nreccollecttotal,pc.paydate getday from zl_housesource  h "
				+"join zl_parkcontract_b pb on(h.pk_housesource=pb.parknum and nvl(pb.dr, 0) = 0 and nvl(h.dr,0) = 0) "
				+"join zl_parkcontract p on(pb.pk_parkcontract=p.pk_parkcontract and nvl(p.dr,0) = 0 and p.vbillstatus=1 and p.version=-1) "
				+"join zl_parkcontract_c pc on(pc.pk_parkcontract=pb.pk_parkcontract and nvl(pc.dr,0) = 0 and pb.parknum=pc.parknum) "
				+"group by h.pk_housesource,pb.pk_customer,pc.paydate) a2 "+jie3
				+" union all "
				+"select * from(select h.pk_housesource pk_house,z.khname pk_customer,(sum(case when zs.ygsmoney is null then 0 else zs.ygsmoney end)) nreccollecttotal,zs.finishtime getday from zl_housesource  h "
				+"join zl_zylist z on(z.fcname=h.pk_housesource and nvl(h.dr,0)=0 and nvl(z.dr,0)=0 and z.liststate=1) "
				+"join zl_zylist_sr zs on(zs.pk_zylist=z.pk_zylist and nvl(zs.dr,0)=0) "
				+"group by h.pk_housesource,z.khname,zs.finishtime) a3  "+jie3+") a4 group by a4.pk_house,a4.pk_customer)b1 "
				+"on (h.pk_housesource=b1.pk_house )"
				+"left join "
				+"(select a5.pk_house,sum(a5.ncollecttotal) ncollecttotal,sum(a5.nrefundtotal) nrefundtotal,a5.getday from(select h.pk_housesource pk_house,(case when gb.nrecmoney>0 then (nvl(gb.nskmny,0) + nvl(gb.nysmny,0)) else 0 end) ncollecttotal,(case when gb.nrecmoney<0 then (abs(nvl(gb.nskmny,0) + nvl(gb.nysmny,0))) else 0 end) nrefundtotal,g.dbilldate getday  "
				+"from zl_housesource h "
				+"join zl_gather_b gb on(h.pk_housesource=gb.pk_house and nvl(gb.dr, 0) = 0 and nvl(h.dr,0) = 0) "
				+"join zl_gather g on(g.pk_gather=gb.pk_gather and g.vbillstatus=1 and nvl(g.dr,0)=0) "
				+")a5 "+jie3+" group by a5.pk_house,a5.getday)b2 on (b2.pk_house=h.pk_housesource ) " 
				+"join zl_project zp on(h.projectname=zp.pk_project) "
				+"join org_orgs og on(og.pk_org=h.pk_org)"
				+"where nvl(h.dr,0) = 0 ) m where  " +where1
				+" order by  m.pk_orgpk, m.prject_pk,m.pk_buildno,to_number(floorn) desc,to_number(unit) asc,to_number(roomnumber) asc";*/
		

		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String , Object>> listmap1 = new ArrayList<Map<String,Object>>();
		List<Map<String , Object>> listmap=(List<Map<String , Object>>)iQ.executeQuery(sql, new MapListProcessor());
		if(listmap==null||listmap.size()<=0){
			ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
			return ;
		}
		BillModel hmodel = billForm.getBillCardPanel().getBillModel();
		//过滤
		if(listmap!=null&&listmap.size()>0){
			UFDouble j = new UFDouble(0);
			UFDouble jud = new UFDouble(0);
			jud = jud.add(j, 0, 0);
			for(Map<String, Object> map : listmap){
					Integer state=(Integer) map.get("housestate");
					switch (state) {
					case 0:
						map.put("housestate", "空置");
						break;
					case 1:
						map.put("housestate", "自用");
						break;
					case 2:
						map.put("housestate", "定租");
						break;
					case 3:
						map.put("housestate", "已租");
						break;
					case 4:
						map.put("housestate", "已售");
						break;
					}
					listmap1.add(map);
			}
		}
		//获取项目、楼栋小计个数
		Map<Object, Integer> map_p = new HashMap<Object, Integer>();
		Map<Object, Integer> map_b = new HashMap<Object, Integer>();
		if(listmap1!=null&&listmap1.size()>0){
			String j1 = "";
			String j1_1 = "";
			String j2 = "";
			String j2_1 = "";
			int count1=0;
			int count2=0;
			for(Map<String, Object> map : listmap1){
				j1 = getStrObj(map.get("prject_pk"));
				j2 = getStrObj(map.get("pk_buildno"));
				if(j1.equals(j1_1)&&j1!=null&&j2.equals(j2_1)&&j2!=null){
					continue;
				}
				for(Map<String, Object> map1 : listmap1){
					j1_1 = getStrObj(map1.get("prject_pk"));
					j2_1 = getStrObj(map1.get("pk_buildno"));
					if(j1.equals(j1_1)&&j1!=null){
						count1++;
					}
					if(j2.equals(j2_1)&&j2!=null){
						count2++;
					}
				}
				j1_1 = getStrObj(map.get("prject_pk"));
				j2_1 = getStrObj(map.get("pk_buildno"));
				
				map_p.put(map.get("prject_pk"), count1);
				map_b.put(map.get("pk_buildno"), count2);
				count1=0;
				count2=0;
			}
		}
		if(listmap1!=null&&listmap1.size()>0){
			//楼栋
			Object up_b_value = null;
			int count1 = 0;
			UFDouble zmj=new UFDouble(0);
			UFDouble cmj=new UFDouble(0);
			UFDouble nys = new UFDouble(0);
			UFDouble nss = new UFDouble(0);
			UFDouble nqf = new UFDouble(0);
			//项目
			Object up_p_value = null;
			int count2 = 0;
			UFDouble zmj_1=new UFDouble(0);
			UFDouble cmj_1=new UFDouble(0);
			UFDouble nys_1 = new UFDouble(0);
			UFDouble nss_1 = new UFDouble(0);
			UFDouble nqf_1 = new UFDouble(0);
			for(Map<String, Object> map : listmap1){
				hmodel.addLine();
				for(String key : map.keySet()){
					hmodel.setValueAt(map.get(key), hmodel.getRowCount()-1, key);
				}
				if(hmodel.getRowCount()-1==0){
					hmodel.setValueAt(cond1, hmodel.getRowCount()-1,"condition" );
					hmodel.setValueAt(jie3, hmodel.getRowCount()-1,"s_type" );
				}
				
				//楼栋小计

				String b = getStrObj(map.get("pk_buildno")) ;
				if((b.equals(getStrObj(up_b_value)))&&!b.equals("")){
					count1++;
					
					zmj=zmj.add(getUfdObj(map.get("rentarea")));
					cmj=cmj.add(getUfdObj(map.get("certificatearea")));
					nys = nys.add(getUfdObj(map.get("nreccollecttotal")));
					nss = nss.add(getUfdObj(map.get("ncollecttotal")));
					nqf = nqf.add(getUfdObj(map.get("narrearagetotal")));
					
				}else if(getStrObj(up_b_value).equals("")&&!b.equals("")){
					count1=1;
					zmj=zmj.add(getUfdObj(map.get("rentarea")));
					cmj=cmj.add(getUfdObj(map.get("certificatearea")));
					nys = nys.add(getUfdObj(map.get("nreccollecttotal")));
					nss = nss.add(getUfdObj(map.get("ncollecttotal")));
					nqf = nqf.add(getUfdObj(map.get("narrearagetotal")));
					up_b_value = b;
				}
				
				if(map_b.get(b).equals(count1)&&count1!=0){
					hmodel.addLine();
					for(String key : map.keySet()){
						if(key.equals("pk_project")){
							hmodel.setValueAt("楼栋小计", hmodel.getRowCount()-1, key);
						}else if(key.equals("rentarea")){
							hmodel.setValueAt(zmj, hmodel.getRowCount()-1, key);
						}else if(key.equals("certificatearea")){
							hmodel.setValueAt(cmj, hmodel.getRowCount()-1, key);
						}else if(key.equals("nreccollecttotal")){
							hmodel.setValueAt(nys, hmodel.getRowCount()-1, key);
						}else if(key.equals("ncollecttotal")){
							hmodel.setValueAt(nss, hmodel.getRowCount()-1, key);
						}else if(key.equals("narrearagetotal")){
							hmodel.setValueAt(nqf, hmodel.getRowCount()-1, key);
						}
						
					}
					//添加背景色
					for(int i=hmodel.getItemIndex("pk_project");i<16;i++){
						hmodel.setBackground(new Color(255, 238, 188), hmodel.getRowCount()-1, i);
					}
					zmj=new UFDouble(0);
					cmj=new UFDouble(0);
					nys = new UFDouble(0);
					nss = new UFDouble(0);
					nqf = new UFDouble(0);
					count1=0;
					up_b_value="";
				}
				//项目小计
				String p = getStrObj(map.get("prject_pk")) ;
				if((p.equals(getStrObj(up_p_value)))&&!p.equals("")){
					count2++;
					
					zmj_1=zmj_1.add(getUfdObj(map.get("rentarea")));
					cmj_1=cmj_1.add(getUfdObj(map.get("certificatearea")));
					nys_1 = nys_1.add(getUfdObj(map.get("nreccollecttotal")));
					nss_1 = nss_1.add(getUfdObj(map.get("ncollecttotal")));
					nqf_1 = nqf_1.add(getUfdObj(map.get("narrearagetotal")));
					
				}else if(getStrObj(up_p_value).equals("")&&!p.equals("")){
					count2=1;
					zmj_1=zmj_1.add(getUfdObj(map.get("rentarea")));
					cmj_1=cmj_1.add(getUfdObj(map.get("certificatearea")));
					nys_1 = nys_1.add(getUfdObj(map.get("nreccollecttotal")));
					nss_1 = nss_1.add(getUfdObj(map.get("ncollecttotal")));
					nqf_1 = nqf_1.add(getUfdObj(map.get("narrearagetotal")));
					up_p_value = p;
				}
				
				if(map_p.get(p).equals(count2)&&count2!=0){
					hmodel.addLine();
					for(String key : map.keySet()){
						if(key.equals("pk_org")){
							hmodel.setValueAt("项目小计", hmodel.getRowCount()-1, key);
						}else if(key.equals("rentarea")){
							hmodel.setValueAt(zmj_1, hmodel.getRowCount()-1, key);
						}else if(key.equals("certificatearea")){
							hmodel.setValueAt(cmj_1, hmodel.getRowCount()-1, key);
						}else if(key.equals("nreccollecttotal")){
							hmodel.setValueAt(nys_1, hmodel.getRowCount()-1, key);
						}else if(key.equals("ncollecttotal")){
							hmodel.setValueAt(nss_1, hmodel.getRowCount()-1, key);
						}else if(key.equals("narrearagetotal")){
							hmodel.setValueAt(nqf_1, hmodel.getRowCount()-1, key);
						}
						
					}
					//添加背景色
					for(int i=hmodel.getItemIndex("pk_org");i<16;i++){
						hmodel.setBackground(new Color(255, 238, 188), hmodel.getRowCount()-1, i);
					}
					zmj_1=new UFDouble(0);
					cmj_1=new UFDouble(0);
					nys_1 = new UFDouble(0);
					nss_1 = new UFDouble(0);
					nqf_1 = new UFDouble(0);
					count2=0;
					up_p_value="";
				}
			}
		}
	}
	private UFDouble getUfdObj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	
	private String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}