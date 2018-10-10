package nc.ui.zl.ly_report.ace.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
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
 * 收费科目明细表查询
 * @author Liu
 *
 */
public class QueryActionCPD extends DefaultQueryAction {
	private static final long serialVersionUID = 5953276677401427168L;

	private ShowUpableBillForm bill;

	public void doAction(ActionEvent e) throws Exception {

		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {

			BillModel bmodel = bill.getBillCardPanel().getBillModel();
			bmodel.clearBodyData();
			bill.getBillCardPanel().setTatolRowShow(true);
			this.processQuery2();
			
			if (bmodel.getRowCount() <= 0) {
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return;
			}
			
			bmodel.execLoadFormula();
			if(bmodel.getRowCount()>0){
				bmodel.addLine();
				UFDouble yszj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("yszj"));
				UFDouble sszj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("sszj"));
				UFDouble dqtk=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("dqtk"));
				UFDouble qfzj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("qfzj"));
				UFDouble nozj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("nocont"));
				
				bmodel.setValueAt(yszj.div(2), bmodel.getRowCount()-1, "yszj");
				bmodel.setValueAt(sszj.div(2), bmodel.getRowCount()-1, "sszj");
				bmodel.setValueAt(dqtk.div(2), bmodel.getRowCount()-1, "dqtk");
				bmodel.setValueAt(qfzj.div(2), bmodel.getRowCount()-1, "qfzj");
				bmodel.setValueAt(nozj.div(2), bmodel.getRowCount()-1, "nocont");
				bmodel.setValueAt("总计", bmodel.getRowCount()-1, "pk_org");
				for(int i=0;i<10;i++){
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
				where3=" and "+where2.replaceAll("x.time", "r.begindate");
				where5=" and "+where2.replaceAll("x.time", "g.dbilldate");
			}
			if(where.contains("x.qfzj")){
				where4 = where.substring(where.indexOf("x.qfzj"),where.indexOf("x.qfzj")+(where.length()-where.indexOf("x.qfzj")-1));
				where = where.replaceAll(where4, " 1=1 ");
				where4 =" where "+ where4.replaceAll("x.qfzj", "欠费总计");
				System.out.println(where4);
			}
			
			String sql = "select * from (select x.pk_org,x.pk_project as 园区名称,x.name as 组织,x.pk_costproject as 收费项目,sum(x.yszj) as 应收总计,sum(x.sszj) as 实收总计" +
					",sum(dqtk) as 当期退款,sum(x.yszj)-(sum(x.sszj)) as 欠费总计,xy.nocont from (" +
					"select a.pk_org,a.name,s.pk_project,s.pk_costproject,s.yszj,s.sszj,s.dqtk,s.time from " +
					"(select pk_org,name from org_orgs where nvl(dr,0)=0) a  join (" +
					"select r.pk_org,r.pk_project,r.pk_costproject,nvl(r.nrecmoney,0) yszj,nvl(gb.nskmny,0) sszj,0 dqtk,r.begindate time from zl_recbill r "+
					"join zl_costproject ct on(ct.pk_costproject=r.pk_costproject and ct.code not in('05','06')) "+
					"left join zl_gather_b gb on( nvl(gb.dr,0)=0 and r.pk_recbill=gb.vsrcid "+
					"and exists(select 1 from zl_gather g where nvl(g.dr, 0) = 0 and g.vbillstatus = 1 and g.pk_gather = gb.pk_gather))"+
					"where nvl(r.dr,0)=0 and r.vdef2 !='zl_contract_bzj' and r.vbillstatus='1' "+where3+
					/*" union all " +
					"(select g.pk_org,g.pk_project,gb.pk_costproject,0," +
					"(case when gb.nskmny>0 then gb.nskmny else 0 end)," +
					"(case when gb.nysmny<0 then abs(gb.nysmny) else 0 end)+(case when gb.nskmny<0 then abs(gb.nskmny) else 0 end),g.dbilldate " +
					"from zl_gather g left join zl_gather_b gb on g.pk_gather=gb.pk_gather and exists(select 1 from zl_costproject ct where ct.pk_costproject=gb.pk_costproject and ct.code not in ('05', '06') ) " +
					"where nvl(g.dr,0)=0 and nvl(gb.dr,0)=0 and g.vbillstatus=1 and gb.pk_costproject is not null and gb.pk_costproject!='~' and g.isadd='N' "+where5+" )"*/ 
					") s on a.pk_org=s.pk_org) x " +
					"left join (select * from (select g.pk_org,g.pk_project,sum(nvl(gb.nskmny,0)) nocont,gb.pk_costproject from zl_gather g "
						+"join zl_gather_b gb on(gb.pk_gather=g.pk_gather and nvl(gb.dr,0)=0) where nvl(g.dr,0)=0 and g.vbillstatus=1 and g.isadd='Y' "
						+"group by  gb.pk_costproject,g.pk_org,g.pk_project)) xy on(x.pk_org=xy.pk_org and x.pk_project=xy.pk_project and x.pk_costproject=xy.pk_costproject)"+
					"where "+where+" group by x.pk_org,x.name,x.pk_project,x.pk_costproject,xy.nocont order by x.name,x.pk_project,x.pk_costproject) "+where4;
			
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

			List<Map<String, Object>> clist = (List<Map<String, Object>>) iQ.executeQuery(sql, new MapListProcessor());

			if (clist != null && clist.size() > 0) {
				String name="";
				Integer j=-1;
				Double ysxj=0.0;
				Double ssxj=0.0;
				Double dqtkxj=0.0;
				Double qfxj=0.0;
				Double noxj=0.0;
				for (int i = 0; i < clist.size(); i++) {
					bmodel.addLine();
					j++;
					if(name.equals("")){
						name=clist.get(i).get("园区名称").toString();
					}
					if(!name.equals(clist.get(i).get("园区名称").toString())){
						name=clist.get(i).get("园区名称").toString();
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_org"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_project"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_costproject"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("yszj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("sszj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("dqtk"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("qfzj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("nocont"));
						bmodel.setValueAt("小计", j, "pk_org");
						bmodel.setValueAt(ysxj, j, "yszj");
						bmodel.setValueAt(ssxj, j, "sszj");
						bmodel.setValueAt(dqtkxj, j, "dqtk");
						bmodel.setValueAt(qfxj, j, "qfzj");
						bmodel.setValueAt(noxj, j, "nocont");
						ysxj=0.0;
						ssxj=0.0;
						dqtkxj=0.0;
						qfxj=0.0;
						noxj=0.0;
						bmodel.addLine();
						j++;
					}
					bmodel.setValueAt(clist.get(i).get("园区名称"), j, "pk_project");
					bmodel.setValueAt(clist.get(i).get("组织"), j, "pk_org");
					bmodel.setValueAt(clist.get(i).get("收费项目"), j, "pk_costproject");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("应收总计")), j, "yszj");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("实收总计")), j, "sszj");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("当期退款")), j, "dqtk");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("欠费总计")), j, "qfzj");
					bmodel.setValueAt(getUfdObj(clist.get(i).get("nocont")), j, "nocont");
					
					ysxj+=Double.parseDouble(clist.get(i).get("应收总计").toString());
					ssxj+=Double.parseDouble(clist.get(i).get("实收总计").toString());
					dqtkxj+=Double.parseDouble(clist.get(i).get("当期退款").toString());
					qfxj+=Double.parseDouble(clist.get(i).get("欠费总计").toString());
					noxj+=Double.parseDouble(getDouStrObj(clist.get(i).get("nocont")));
					//bmodel.execLoadFormulaByRow(j);
					if(i==clist.size()-1){
						bmodel.addLine();
						j++;
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_org"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_project"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("pk_costproject"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("yszj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("sszj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("dqtk"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("qfzj"));
						bmodel.setBackground(new Color(255, 238, 188),j, bmodel.getItemIndex("nocont"));
						bmodel.setValueAt("小计", j, "pk_org");
						bmodel.setValueAt(ysxj, j, "yszj");
						bmodel.setValueAt(ssxj, j, "sszj");
						bmodel.setValueAt(dqtkxj, j, "dqtk");
						bmodel.setValueAt(qfxj, j, "qfzj");
						bmodel.setValueAt(noxj, j, "nocont");
					}
				}
				
				bmodel.setValueAt(cond1, 0, "condition");
			}

			
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	private UFDouble getUfdObj(Object obj) {
		return obj == null ? new UFDouble(0) : new UFDouble(obj.toString());
	}
	private String getDouStrObj(Object obj) {
		return obj == null ? "0" : obj.toString();
	}
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}