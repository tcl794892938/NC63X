package nc.ui.zl.ly_report.ace.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 主要指标统计查询
 * @author Liu
 *
 */
public class QueryActionZBTJ extends DefaultQueryAction {
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
			bill.getBillCardPanel().setTatolRowShow(true);
			UFDouble yshj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ysmny"));
			UFDouble sshj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ssmny"));
			Double sjhj=0.0;
			if(yshj.doubleValue()!=0){
				sjhj=sshj.div(yshj).doubleValue()*100;
			}
			Double sjhj1=new BigDecimal(sjhj).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			bill.getBillCardPanel().getTotalTableModel().setValueAt(sjhj1, 0, bmodel.getItemIndex("sjrent"));
			
			UFDouble kzhj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("kzarea"));
			UFDouble czhj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("czarea"));
			Double czlhj=0.0;
			if(kzhj.doubleValue()!=0){
				czlhj=czhj.div(kzhj).doubleValue()*100;
			}
			Double czlhj1=new BigDecimal(czlhj).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			bill.getBillCardPanel().getTotalTableModel().setValueAt(czlhj1, 0, bmodel.getItemIndex("czrent"));
			//bmodel.setBackground(new Color(255, 238, 188), bmodel.getRowCount()-1, bmodel.getItemIndex("sjrent"));
			
			//总计
			if(bmodel.getRowCount()>0){
				bmodel.addLine();
				UFDouble yszj=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ysmny")));
				UFDouble sszj=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ssmny")));
				UFDouble dqtk=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("sjrent")));
				UFDouble qfzj=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("kzarea")));
				UFDouble nozj=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("nocont")));
				UFDouble ozarea=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("czarea")));
				UFDouble czrent=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("czrent")));
				UFDouble dayprice=getUfdObj(bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("dayprice")));
				
				bmodel.setValueAt(yszj, bmodel.getRowCount()-1, "ysmny");
				bmodel.setValueAt(sszj, bmodel.getRowCount()-1, "ssmny");
				bmodel.setValueAt(dqtk, bmodel.getRowCount()-1, "sjrent");
				bmodel.setValueAt(qfzj, bmodel.getRowCount()-1, "kzarea");
				bmodel.setValueAt(nozj, bmodel.getRowCount()-1, "nocont");
				bmodel.setValueAt(ozarea, bmodel.getRowCount()-1, "czarea");
				bmodel.setValueAt(czrent, bmodel.getRowCount()-1, "czrent");
				//bmodel.setValueAt(dayprice.div(bmodel.getRowCount()-1), bmodel.getRowCount()-1, "dayprice");
				bmodel.setValueAt("总计", bmodel.getRowCount()-1, "pk_org");
				
				for(int i=0;i<11;i++){
					bmodel.setBackground(new Color(255, 255, 188), bmodel.getRowCount()-1, i);
				}
				bill.getBillCardPanel().setTatolRowShow(false);
				
				
			}
			
			
		}

	}

	@SuppressWarnings("unchecked")
	private void processQuery2() {
		try {
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();

			FromWhereSQLImpl obj2 = (FromWhereSQLImpl) queryScheme.getTableJoinFromWhereSQL();
			BillModel bmodel = bill.getBillCardPanel().getBillModel();
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

			String cond=queryScheme.get("description").toString();
			String cond1="";
			if(cond.startsWith("(")){
				cond1=cond.substring(1, cond.length()-1);
			}else{
				cond1=cond.substring(0, cond.length());
			}
			
			String where = obj2.getWhere();
			where=where.replaceAll("zl_zbzj.", "x.");
			String where1=where;
			String where2="1=1";
			String where3="";
			String where4="";
			String where5="";
			String where6="";
			String where7="";
			String where8="";
			
			if(where.contains("x.time")){
				int ts=0;
				for(int i=0;i<where.length()-6;i++){
					String t=where.substring(i,i+6);
					if(t.equals("x.time")){
						ts++;
					}
				}
				if(ts==1){
					where2=where1.substring(where1.indexOf("x.time")-1, where1.indexOf("x.time")+31);
				}
				if(ts==2){
					where2=where1.substring(where1.indexOf("x.time")-1, where1.indexOf("x.time")+68);
				}
				where=where.replaceAll(where2, " 1=1 ");
				where1=where1.replaceAll(where2, " 1=1 ");
				where2=where2.replaceAll("x.time", "c.drentdate");
				where3=" and "+where2.replaceAll("c.drentdate", "r.begindate");
				where4=" and "+where2.replaceAll("c.drentdate", "g.dbilldate");
				//判断是否有“<=”  
				String d1 = "";
				if(where2.indexOf("<=")!=-1){
					System.out.println(where2.substring(where2.indexOf("<=")+2, where2.indexOf("<=")+24));
					d1 = where2.substring(where2.indexOf("<=")+2, where2.indexOf("<=")+24);
					where5=" and c.dstartdate <= "+d1;
					where6=" and p.startdate <= "+d1;
					where7=" and zc.dstartdate <= "+d1+" and zc.denddate >= "+d1 ;
					where8=" and c.dstartdate <= "+d1+" and c.denddate >= "+d1 ;
				}else{
					Date d = new Date();  
			        
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			        d1 = sdf.format(d); 
			        where5=" and c.dstartdate <= '"+d1+"'";
					where6=" and p.startdate <= '"+d1+"'";
					where7=" and zc.dstartdate <= '"+d1+"' and zc.denddate>='"+d1+"'";
					where8=" and c.dstartdate <= '"+d1+"' and c.denddate>='"+d1+"'";
				}
				
			}else{
				Date d = new Date();  
				String d1 = "";
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		        d1 = sdf.format(d); 
		        where7=" and zc.dstartdate <= '"+d1+"' and zc.denddate>='"+d1+"'";
		        where8=" and c.dstartdate <= '"+d1+"' and c.denddate>='"+d1+"'";
			}
			String sql1 = "select x.proname as 项目名称,sum(x.ysmny) as 应收金额,sum(x.ssmny) as 实收金额, (case when sum(x.ysmny)=0 then 0 else round(sum(x.ssmny) / sum(x.ysmny), 4) * 100 end) as 收缴率,sum(nocont) as 无合同收费 "
						+"from("
						+"select a3.pk_org,a3.proname,a3.ysmny,a3.ssmny,0 nocont from(select r.pk_org,r.pk_project proname,sum(nvl(r.nrecmoney,0)) ysmny,0 ssmny "
						+"from zl_recbill r join zl_costproject ct on(ct.pk_costproject=r.pk_costproject and ct.code not like '05%' and ct.code not like '06%') "
						+"where nvl(r.dr,0)=0 and r.vdef2 not in('zl_contract_bzj' )"+where3+" group by r.pk_org,r.pk_project)a3 " 
						+"union all " +
						"select * from (select g.pk_org,g.pk_project proname,0 ysmny,sum(nvl(gb.nskmny, 0)) ssmny,0 nocont from zl_gather g " +
						"join zl_gather_b gb on (gb.pk_gather = g.pk_gather and nvl(gb.dr, 0) = 0) " +
						"join zl_costproject ct on (ct.pk_costproject = gb.pk_costproject and ct.code not in ('05', '06')) " +
						"where nvl(g.dr, 0) = 0 and g.vbillstatus = 1 and g.isadd = 'N' group by g.pk_org, g.pk_project) a5 " +
						"union all "
						+"select * from (select g.pk_org,g.pk_project proname,0 ysmny,0 ssmny,sum(nvl(gb.nskmny,0)) nocont from zl_gather g "
						+"join zl_gather_b gb on(gb.pk_gather=g.pk_gather and nvl(gb.dr,0)=0) where nvl(g.dr,0)=0 and g.vbillstatus=1 and g.isadd='Y' "
						+"group by  g.pk_org,g.pk_project)a4)x where "+where
						+" group by x.pk_org,x.proname order by x.pk_org,x.proname";
			/*String sql1="select x.proname as 项目名称,sum(x.ysmny) as 应收金额,sum(x.ssmny) as 实收金额," +
					"(case when sum(x.ysmny)=0 then 0 else round(sum(x.ssmny) / sum(x.ysmny), 4) * 100 end) as 收缴率 from (" +
					"(select c.pk_org,c.pk_project proname,cb.nysmny ysmny,0 ssmny,cb.drecdate time from zl_contract c " +
					"left join zl_contract_bzj cb on cb.pk_contract=c.pk_contract " +
					"where nvl(cb.dr,0)=0 and nvl(c.dr,0)=0 and c.vbillstatus=1 and c.version=-1) " +
					"union all " +
					"(select c.pk_org,c.pk_project,cy.nysmny,0,cy.drecdate from zl_contract c " +
					"left join zl_contract_ywcf cy on cy.pk_contract=c.pk_contract " +
					"where nvl(cy.dr,0)=0 and nvl(c.dr,0)=0 and c.vbillstatus=1 and c.version=-1) " +
					"union all " +
					"(select c.pk_org,c.pk_project,cz.nysmny,0,cz.drecdate from zl_contract c " +
					"left join zl_contract_zqfycf cz on c.pk_contract=cz.pk_contract " +
					"where nvl(c.dr,0)=0 and nvl(cz.dr,0)=0 and c.vbillstatus=1 and c.version=-1) " +
					"union all " +
					"(select ca.pk_org,ca.pk_project,cc.nreceivemoney,0,cc.paydate from zl_carcontract ca " +
					"left join zl_carcontract_c cc on ca.pk_carcontract=cc.pk_carcontract and ca.version=-1" +
					"where nvl(ca.dr,0)=0 and nvl(cc.dr,0)=0 and ca.vbillstatus=1) " +
					"union all " +
					"(select pa.pk_org,pa.pk_project,pc.nreceivemoney,0,pc.paydate from zl_parkcontract pa " +
					"left join zl_parkcontract_c pc on pa.pk_parkcontract=pc.pk_parkcontract " +
					"where nvl(pa.dr,0)=0 and nvl(pc.dr,0)=0 and pa.vbillstatus=1 and pa.version=-1) " +
					"union all " +
					"(select m.pk_org,m.pk_project,mc.receivemoney,0,mc.moneydate from zl_mdcontract m " +
					"left join zl_mdcontract_c mc on m.pk_mdcontract=mc.pk_mdcontract " +
					"where nvl(m.dr,0)=0 and nvl(mc.dr,0)=0 and m.state=1 and m.version=-1) " +
					"union all " +
					"(select py.pk_org,py.pk_project,pb.nysmny,0,pb.dysdate from zl_payment py " +
					"left join zl_payment_b pb on py.pk_payment=pb.pk_payment where nvl(py.dr,0)=0 and nvl(pb.dr,0)=0 and py.vbillstatus=1) " +
					"union all " +
					"(select pr.pk_org,pr.pk_project,prb.nysmny,0,prb.dysdate from zl_prepayment pr " +
					"left join zl_prepayment_b prb on pr.pk_prepayment=prb.pk_prepayment where nvl(pr.dr,0)=0 and nvl(prb.dr,0)=0 and pr.vbillstatus=1) " +
					"union all " +
					"(select z.pk_org,z.pk_project,zs.ygsmoney,0,zs.finishtime from zl_zylist z " +
					"left join zl_zylist_sr zs on z.pk_zylist=zs.pk_zylist where nvl(z.dr,0)=0 and nvl(zs.dr,0)=0 and z.liststate=1) " +
					"union all " +
					"(select g.pk_org,g.pk_project,0,(case when gb.nskmny is null then 0 else gb.nskmny end)+" +
					"(case when gb.nysmny is null then 0 else gb.nysmny end),g.dbilldate from zl_gather g " +
					"left join zl_gather_b gb on g.pk_gather=gb.pk_gather where nvl(g.dr,0)=0 and nvl(gb.dr,0)=0 and g.vbillstatus=1)) x " +
					"where "+where+" group by x.pk_org,x.proname order by x.pk_org,x.proname";*/
			List<Map<String, Object>> clist1 = (List<Map<String, Object>>) iQ.executeQuery(sql1, new MapListProcessor());
			
			/*String sql2="select x.pk_org as 组织,x.proname as 项目名称,sum(x.kzarea) as 可租面积,sum(x.czarea) as 出租面积," +
					"(case when sum(x.kzarea)=0 then 0 else round(sum(x.czarea) / sum(x.kzarea), 4) * 100 end) as 出租率  from (" +
					"(select b.pk_org,b.pk_projectid proname," +
					"(case when h.buildarea is null then 0 else h.buildarea end) kzarea,0 czarea,null time " +
					"from zl_buildingfile b left join zl_housesource h on b.pk_buildingfile=h.buildname " +
					"where nvl(b.dr,0)=0 and nvl(h.dr,0)=0 and h.housestate not in (1,4)) " +
					"union all " +
					"(select c.pk_org,c.pk_project,0," +
					"(case when ch.narea is null then 0 else ch.narea end),c.drentdate from zl_contract_house ch " +
					"left join zl_contract c on ch.pk_contract=c.pk_contract " +
					"where "+where2+" and nvl(c.dr,0)=0 and nvl(ch.dr,0)=0 and c.vbillstatus<>-1 and c.htstatus<>4 and version=-1)" +
					") x where "+where1+" group by x.pk_org,x.proname order by x.pk_org,x.proname";*/
			String sql2="select x.pk_org as 组织,x.proname as 项目名称,sum(x.kzarea) as 可租面积,sum(x.czarea) as 出租面积,"
					 	+"(case when sum(x.kzarea)=0 then 0 else round(sum(x.czarea) / sum(x.kzarea), 4) * 100 end) as 出租率,b3.dayprice  from ("
						+"select h.pk_org,h.projectname proname,sum(nvl(h.buildarea,0)) kzarea, "
						+"0 czarea,null time from zl_housesource h "
						+"where nvl(h.dr,0)=0 and h.housestate not in (1,4) group by h.pk_org,h.projectname "
						+"union all "
						+"select c.pk_org,c.pk_project proname,0 kzarea,sum(nvl(ch.narea,0)) czarea,c.dstartdate time from zl_contract_house ch "
						+"join zl_contract c on(c.pk_contract=ch.pk_contract and nvl(c.dr,0)=0 and nvl(ch.dr,0)=0 and c.vbillstatus in(1,2,3) and c.htstatus<>4 and c.version=-1 "+where5+") "
						+"group by  c.pk_org,c.pk_project,c.dstartdate "
						+"union all "
						+"select p.pk_org,p.pk_project proname,0 kzarea,sum(nvl(h.buildarea,0)) czarea,p.startdate time from zl_parkcontract_b pb "
						+"join zl_parkcontract p on (pb.pk_parkcontract=p.pk_parkcontract and nvl(p.dr,0)=0 and nvl(pb.dr,0)=0 and p.vbillstatus in (1, 2, 3)  and p.version=-1 "+where6+") "
						+"join zl_housesource h on(pb.parknum=h.pk_housesource and nvl(h.dr,0)=0) "
						+" group by  p.pk_org,p.pk_project,p.startdate) x "
						+"join (select * from (select b1.pk_org,b1.proname,sum(b1.yearmny)/365/sum(b2.area) dayprice  from(select bb.pk_org,bb.proname,sum(bb.yearmny) yearmny from(select c.pk_org,c.pk_project proname,nvl(sum(zc.nyear2mny),0)/count(zc.pk_contract_zjmx) yearmny from zl_contract_zjmx zc "
						+"join zl_contract c on(c.pk_contract=zc.pk_contract and nvl(c.dr,0)=0 and nvl(zc.dr,0)=0 and c.vbillstatus=1 and c.htstatus<>4 and c.version=-1) "+where7
						+" group by c.pk_org,c.pk_project,c.pk_contract)bb group by bb.pk_org,bb.proname) b1,(select c.pk_org,c.pk_project proname,sum(nvl(ch.narea,0)) area from zl_contract_house ch  "
						+"join zl_contract c on(c.pk_contract=ch.pk_contract and nvl(c.dr,0)=0 and nvl(ch.dr,0)=0 and c.vbillstatus=1 and c.htstatus<>4 and c.version=-1 "+where8+") "
						+"where exists(select 1 from zl_contract_zjmx zc where nvl(zc.dr,0)=0 and zc.pk_contract=ch.pk_contract and zc.nyear2mny<>0 ) "
						+"group by c.pk_org,c.pk_project) b2 "
						+"where b1.pk_org=b2.pk_org and b1.proname=b2.proname "
						+"group by b1.pk_org,b1.proname)) b3 on(b3.pk_org=x.pk_org and b3.proname=x.proname) " 
						+" where "+where+" group by x.pk_org,x.proname,b3.dayprice order by x.pk_org,x.proname";
			List<Map<String, Object>> clist2 = (List<Map<String, Object>>) iQ.executeQuery(sql2, new MapListProcessor());

			if(clist2!=null&&clist2.size()>0){
				for(int i=0;i<clist2.size();i++){
					bmodel.addLine();
					bmodel.setValueAt(clist2.get(i).get("组织"), i, "pk_org");
					bmodel.setValueAt(clist2.get(i).get("项目名称"), i, "proname");
					bmodel.setValueAt(0, i, "ysmny");
					bmodel.setValueAt(0, i, "ssmny");
					bmodel.setValueAt(0, i, "sjrent");
					bmodel.setValueAt(getUfdObj(clist2.get(i).get("可租面积")), i, "kzarea");
					bmodel.setValueAt(getUfdObj(clist2.get(i).get("出租面积")), i, "czarea");
					bmodel.setValueAt(getUfdObj(clist2.get(i).get("出租率")), i, "czrent");
					bmodel.setValueAt(getUfdObj(clist2.get(i).get("dayprice")), i, "dayprice");
				}
			}
			
			if (clist1 != null && clist1.size() > 0) {
				for(int j=0;j<bmodel.getRowCount();j++){
					for (int i = 0; i < clist1.size(); i++) {
							if(clist1.get(i).get("项目名称").equals(bmodel.getValueAt(j, "proname"))){
								bmodel.setValueAt(getUfdObj(clist1.get(i).get("应收金额")), j, "ysmny");
								bmodel.setValueAt(getUfdObj(clist1.get(i).get("实收金额")), j, "ssmny");
								bmodel.setValueAt(getUfdObj(clist1.get(i).get("收缴率")), j, "sjrent");
								bmodel.setValueAt(getUfdObj(clist1.get(i).get("无合同收费")), j, "nocont");
							}
					}
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