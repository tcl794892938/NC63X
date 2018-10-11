package nc.ui.zl.ly_report.ace.view;

import java.awt.Color;
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
import nc.vo.pub.lang.UFDouble;

/**
 * 保证金查询
 * @author Liu
 *
 */
public class QueryActionBZJ extends DefaultQueryAction {
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
				UFDouble yszj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ysbzj"));
				UFDouble sszj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ssbzj"));
				UFDouble ytzj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("ytbzj"));
				UFDouble stzj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("stbzj"));
				UFDouble yqrzj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("yqrbzj"));
				UFDouble yezj=(UFDouble) bill.getBillCardPanel().getTotalTableModel().getValueAt(0, bmodel.getItemIndex("bzjye"));
				
				bmodel.setValueAt("总计", bmodel.getRowCount()-1, "pk_org");
				bmodel.setValueAt(yszj.div(3), bmodel.getRowCount()-1, "ysbzj");
				bmodel.setValueAt(sszj.div(3), bmodel.getRowCount()-1, "ssbzj");
				bmodel.setValueAt(ytzj.div(3), bmodel.getRowCount()-1, "ytbzj");
				bmodel.setValueAt(stzj.div(3), bmodel.getRowCount()-1, "stbzj");
				bmodel.setValueAt(yqrzj.div(3), bmodel.getRowCount()-1, "yqrbzj");
				bmodel.setValueAt(yezj.div(3), bmodel.getRowCount()-1, "bzjye");
				for(int i=0;i<bmodel.getColumnCount();i++){
					bmodel.setBackground(new Color(255, 255, 188), bmodel.getRowCount()-1, i);
				}
				bill.getBillCardPanel().setTatolRowShow(false);
			
			}
			
		}

	}

	@SuppressWarnings("unchecked")
	private void processQuery2() throws Exception {
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();

			FromWhereSQLImpl obj2 = (FromWhereSQLImpl) queryScheme.getTableJoinFromWhereSQL();
			
			String cond=queryScheme.get("description").toString();
			String cond1="";
			if(cond.startsWith("(")){
				cond1=cond.substring(1, cond.length()-1);
			}else{
				cond1=cond.substring(0, cond.length());
			}

			String where1 = obj2.getWhere();
			String where = where1.replaceAll("zl_zbzj.", " ");
			
			String sql = "select * from (select m.pk_org,m.pk_project,m.pk_customer,m.htcode,m.pk_house,sum(m.ysmny) ysbzj,sum(m.ssmny) ssbzj," +
					"sum(m.ytmny) ytbzj,sum(m.stmny) stbzj,sum(m.yqrmny) yqrbzj,sum(m.ssmny) - sum(m.stmny) - sum(m.yqrmny) bzjye " +
					"from (select r.pk_org,r.pk_project,r.pk_customer,c.htcode,wm_concat((select b.name||'-'||h.roomnumber from zl_buildingfile b " +
					"left join zl_housesource h on b.pk_buildingfile=h.buildname where nvl(b.dr,0)=0 and nvl(h.dr,0)=0 and " +
					"h.pk_housesource=ch.pk_house)) pk_house,(case when r.nrecmoney > 0 then r.nrecmoney else 0 end) ysmny," +
					"(case when r.nrealmoney > 0 then r.nrealmoney else 0 end) ssmny,(case when r.nrecmoney < 0 then abs(r.nrecmoney) else 0 end) ytmny," +
					"(case when r.nrealmoney < 0 then abs(r.nrealmoney) else 0 end) stmny, 0 yqrmny from zl_recbill r " +
					"left join zl_contract c on c.pk_contract = r.vsrcid and nvl(c.dr, 0) = 0 left join zl_contract_house ch on " +
					"ch.pk_contract=c.pk_contract and nvl(ch.dr,0)=0 where nvl(r.dr, 0) = 0 and r.vbillstatus = 1 and r.pk_costproject in " +
					"(select ct.pk_costproject from zl_costproject ct where nvl(ct.dr,0)=0 and ct.code like '05%') " +
					"group by r.pk_org,r.pk_project,r.pk_customer,c.htcode,r.nrecmoney,r.nrealmoney " +
					"union all " +
					"select cf.pk_org,cf.pk_project,cf.pk_customer,c1.htcode,wm_concat((select b.name||'-'||h.roomnumber from zl_buildingfile b " +
					"left join zl_housesource h on b.pk_buildingfile=h.buildname where nvl(b.dr,0)=0 and nvl(h.dr,0)=0 and " +
					"h.pk_housesource=ch1.pk_house)),0,0,0,0," +
					"(case when cf.amountconfirmed is null then 0 else cf.amountconfirmed end) from zl_confirmation cf " +
					"left join zl_contract c1 on c1.pk_contract=cf.vsrcid and nvl(c1.dr,0)=0 left join zl_contract_house ch1 on " +
					"ch1.pk_contract=c1.pk_contract and nvl(ch1.dr,0)=0 where nvl(cf.dr,0)=0 " +
					"and cf.vbillstatus=1 and cf.amountconfirmed<>0 and cf.chargingproject in " +
					"(select ct.pk_costproject from zl_costproject ct where nvl(ct.dr,0)=0 and ct.code like '05%') " +
					"group by cf.pk_org,cf.pk_project,cf.pk_customer,c1.htcode,cf.amountconfirmed " +
					"union all " +
					"select g.pk_org,g.pk_project,g.pk_customer,null,(select b.name||'-'||h.roomnumber from zl_buildingfile b " +
					"left join zl_housesource h on b.pk_buildingfile=h.buildname where nvl(b.dr,0)=0 and nvl(h.dr,0)=0 and " +
					"h.pk_housesource=gb.pk_house), 0,(case when gb.nskmny > 0 then gb.nskmny else 0 end),0," +
					"(case when gb.nskmny < 0 then gb.nskmny else 0 end),0 from zl_gather_b gb left join zl_gather g on g.pk_gather = gb.pk_gather " +
					"where g.isadd = 'Y' and g.vbillstatus = 1 and nvl(g.dr, 0) = 0 and nvl(gb.dr, 0) = 0 and gb.pk_costproject in " +
					"(select ct.pk_costproject from zl_costproject ct where nvl(ct.dr,0)=0 and ct.code like '05%')) m group by m.pk_org,m.pk_project," +
					"m.pk_customer,m.htcode,m.pk_house order by m.pk_org,m.pk_project,m.pk_customer,m.htcode,m.pk_house) where "+where;
			
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

			List<Map<String, Object>> clist = (List<Map<String, Object>>) iQ.executeQuery(sql, new MapListProcessor());

			if (clist.size() <= 0) {
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return;
			}
			BillModel bm = bill.getBillCardPanel().getBillModel();
			int colcount=bm.getColumnCount();
			//用于按客户维度小计
			String cust = clist.get(0).get("pk_customer").toString();
			UFDouble ys1 = new UFDouble(0);
			UFDouble ss1 = new UFDouble(0);
			UFDouble yt1 = new UFDouble(0);
			UFDouble st1 = new UFDouble(0);
			UFDouble yqr1 = new UFDouble(0);
			UFDouble ye1 = new UFDouble(0);
			//用于按项目维度小计
			String pk_proj = clist.get(0).get("pk_project").toString();
			UFDouble ys2 = new UFDouble(0);
			UFDouble ss2 = new UFDouble(0);
			UFDouble yt2 = new UFDouble(0);
			UFDouble st2 = new UFDouble(0);
			UFDouble yqr2 = new UFDouble(0);
			UFDouble ye2 = new UFDouble(0);
			int addCust=0,addProj=0,addThe=0,row=0;
			//输出第一行
			for(int i=0;i<clist.size();i++){
				addCust=0;
				addProj=0;
				addThe=0;
				Map<String,Object> map_some=clist.get(i);
				if(map_some.get("pk_customer")==null){
					System.out.println(i);
					System.out.println(clist.size());
				}
				if(map_some.get("pk_customer").equals(cust)){
				//客户名称相同
					if(map_some.get("pk_project").equals(pk_proj)){
					//项目相同
						//统计客户 项目
						ys1=ys1.add(new UFDouble (map_some.get("ysbzj").toString()));
						ss1=ss1.add(new UFDouble (map_some.get("ssbzj").toString()));
						yt1=yt1.add(new UFDouble( map_some.get("ytbzj").toString()));
						st1=st1.add(new UFDouble (map_some.get("stbzj").toString()));
						yqr1=yqr1.add(new UFDouble( map_some.get("yqrbzj").toString()));
						ye1=ye1.add(new UFDouble (map_some.get("bzjye").toString()));
						ys2=ys2.add(new UFDouble (map_some.get("ysbzj").toString()));
						ss2=ss2.add(new UFDouble (map_some.get("ssbzj").toString()));
						yt2=yt2.add(new UFDouble( map_some.get("ytbzj").toString()));
						st2=st2.add(new UFDouble (map_some.get("stbzj").toString()));
						yqr2=yqr2.add(new UFDouble( map_some.get("yqrbzj").toString()));
						ye2=ye2.add(new UFDouble (map_some.get("bzjye").toString()));
						//增加此行
						addThe=1;
					}else{
					//项目不同
						//增加客户小计行,重置客户名称和小计
						addCust=1;
						//增加项目小计行,重置项目名称和小计
						addProj=1;
						//增加此行
						addThe=1;
					}
				}else{
				//客户名称不同
					if(map_some.get("pk_project").equals(pk_proj)){
					//项目相同
						//统计项目
						ys2=ys2.add(new UFDouble (map_some.get("ysbzj").toString()));
						ss2=ss2.add(new UFDouble (map_some.get("ssbzj").toString()));
						yt2=yt2.add(new UFDouble( map_some.get("ytbzj").toString()));
						st2=st2.add(new UFDouble (map_some.get("stbzj").toString()));
						yqr2=yqr2.add(new UFDouble( map_some.get("yqrbzj").toString()));
						ye2=ye2.add(new UFDouble (map_some.get("bzjye").toString()));
						//增加客户小计行,重置客户名称和小计
						addCust=1;
						//增加此行
						addThe=1;
					}else{
					//项目不同
						//增加客户小计行,重置客户名称和小计
						addCust=1;
						//增加项目小计行,重置项目名称和小计
						addProj=1;
						//增加此行
						addThe=1;
					}
				}
				if(addCust==1){
					bm.addLine();
					bm.setValueAt("客户小计", row, "pk_customer");
					bm.setValueAt(ys1, row, "ysbzj");
					bm.setValueAt(ss1, row, "ssbzj");
					bm.setValueAt(yt1, row, "ytbzj");
					bm.setValueAt(st1, row, "stbzj");
					bm.setValueAt(yqr1, row, "yqrbzj");
					bm.setValueAt(ye1, row, "bzjye");
					for(int k=bm.getItemIndex("pk_customer");k<colcount;k++){
						bm.setBackground(new Color(255, 238, 188),row,k);
					}
					row++;
					cust=(String) map_some.get("pk_customer");
					ys1=new UFDouble (map_some.get("ysbzj").toString());
					ss1=new UFDouble (map_some.get("ssbzj").toString());
					yt1=new UFDouble( map_some.get("ytbzj").toString());
					st1=new UFDouble (map_some.get("stbzj").toString());
					yqr1=new UFDouble( map_some.get("yqrbzj").toString());
					ye1=new UFDouble (map_some.get("bzjye").toString());
				}
				if(addProj==1){
					bm.addLine();
					bm.setValueAt("项目小计", row, "pk_project");
					bm.setValueAt(ys2, row, "ysbzj");
					bm.setValueAt(ss2, row, "ssbzj");
					bm.setValueAt(yt2, row, "ytbzj");
					bm.setValueAt(st2, row, "stbzj");
					bm.setValueAt(yqr2, row, "yqrbzj");
					bm.setValueAt(ye2, row, "bzjye");
					for(int k=bm.getItemIndex("pk_project");k<colcount;k++){
						bm.setBackground(new Color(255, 238, 188),row,k);
					}
					row++;
					pk_proj=(String) map_some.get("pk_project");
					ys2=new UFDouble (map_some.get("ysbzj").toString());
					ss2=new UFDouble (map_some.get("ssbzj").toString());
					yt2=new UFDouble( map_some.get("ytbzj").toString());
					st2=new UFDouble (map_some.get("stbzj").toString());
					yqr2=new UFDouble( map_some.get("yqrbzj").toString());
					ye2=new UFDouble (map_some.get("bzjye").toString());
				}
				if(addThe==1){
					bm.addLine();
					bm.setValueAt(map_some.get("pk_org"), row, "pk_org");
					bm.setValueAt(map_some.get("pk_project"), row, "pk_project");
					bm.setValueAt(map_some.get("pk_customer"), row, "pk_customer");
					bm.setValueAt(map_some.get("htcode"), row, "htcode");
					bm.setValueAt(map_some.get("pk_house"), row, "pk_house");
					bm.setValueAt(map_some.get("ysbzj"), row, "ysbzj");
					bm.setValueAt(map_some.get("ssbzj"), row, "ssbzj");
					bm.setValueAt(map_some.get("ytbzj"), row, "ytbzj");
					bm.setValueAt(map_some.get("stbzj"), row, "stbzj");
					bm.setValueAt(map_some.get("yqrbzj"), row, "yqrbzj");
					bm.setValueAt(map_some.get("bzjye"), row, "bzjye");
					row++;
				}
				//若是最后一行 则增加客户小计行和项目小计行
				if(i==clist.size()-1){
					bm.addLine();
					bm.setValueAt("客户小计", row, "pk_customer");
					bm.setValueAt(ys1, row, "ysbzj");
					bm.setValueAt(ss1, row, "ssbzj");
					bm.setValueAt(yt1, row, "ytbzj");
					bm.setValueAt(st1, row, "stbzj");
					bm.setValueAt(yqr1, row, "yqrbzj");
					bm.setValueAt(ye1, row, "bzjye");
					for(int k=bm.getItemIndex("pk_customer");k<colcount;k++){
						bm.setBackground(new Color(255, 238, 188),row,k);
					}
					row++;
					bm.addLine();
					bm.setValueAt("项目小计", row, "pk_project");
					bm.setValueAt(ys2, row, "ysbzj");
					bm.setValueAt(ss2, row, "ssbzj");
					bm.setValueAt(yt2, row, "ytbzj");
					bm.setValueAt(st2, row, "stbzj");
					bm.setValueAt(yqr2, row, "yqrbzj");
					bm.setValueAt(ye2, row, "bzjye");
					for(int k=bm.getItemIndex("pk_project");k<colcount;k++){
						bm.setBackground(new Color(255, 238, 188),row,k);
					}
				}
			}
				
			if(bm.getRowCount()>0){
				bm.setValueAt(cond1, 0, "condition");
			}
	}

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}