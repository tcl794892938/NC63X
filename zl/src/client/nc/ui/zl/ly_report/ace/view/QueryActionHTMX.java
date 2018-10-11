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
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFDouble;

public class QueryActionHTMX extends DefaultQueryAction{
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
				UFDouble yszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("recmny"));
				UFDouble sszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("realmny"));
				UFDouble qfzj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("qfmny"));
				Double num1=new BigDecimal(yszj.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num2=new BigDecimal(sszj.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num4=new BigDecimal(qfzj.doubleValue()/2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//总计
				hmodel.addLine();
				hmodel.setValueAt("总计", hmodel.getRowCount()-1, "pk_org");
				hmodel.setValueAt(num1, hmodel.getRowCount()-1, "recmny");
				hmodel.setValueAt(num2, hmodel.getRowCount()-1, "realmny");
				hmodel.setValueAt(num4, hmodel.getRowCount()-1, "qfmny");
				for(int k=hmodel.getItemIndex("pk_org");k<hmodel.getColumnCount();k++){
					hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, k);
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
		
		where=where.replaceAll("htmx.", "m.");
		
		String sql_someByHouse="select * from (select x.pk_org,x.pk_project,x.htcode,x.pk_customer,x.house pk_house,x.recdate,x.pk_costproject,x.recmny," +
				"x.realmny,x.recmny - x.realmny qfmny from (select r.pk_org,r.pk_project,(case when r.vsrctype='0001ZZ1000000001SNDJ' then " +
				"(select c.htcode from zl_contract c where c.pk_contract = r.vsrcid) else " +
				"(select t.contractid from zl_throwalease t where t.pk_throwalease=r.vsrcid) end) htcode,r.pk_customer,r.pk_building," +
				"wm_concat((select b.name||'-'||h.roomnumber from zl_buildingfile b left join zl_housesource h on " +
				"b.pk_buildingfile=h.buildname where nvl(b.dr,0)=0 and nvl(h.dr,0)=0 and h.pk_housesource=r.pk_house)) house,r.gatherdate recdate," +
				"r.pk_costproject,sum(r.nrecmoney) recmny,(case when sum(r.nrealmoney) is null then 0 else sum(r.nrealmoney) end) realmny " +
				"from zl_recbill r where nvl(r.dr, 0) = 0 and r.vbillstatus = 1 and r.vsrctype in ('0001ZZ1000000001SNDJ','0001ZZ1000000001UKA6') " +
				"group by r.pk_org,r.pk_project,r.pk_customer,r.pk_costproject,r.pk_building,r.gatherdate,r.vsrcid,r.vsrctype) x order by " +
				"x.pk_org,x.pk_project,x.htcode,x.pk_customer,x.pk_building asc,x.pk_costproject,x.recdate asc) m where "+where;
				
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String , Object>> maps_some=(List<Map<String , Object>>)iQ.executeQuery(sql_someByHouse, new MapListProcessor());
		if(maps_some==null||maps_some.size()<=0){
			ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
			return ;
		}
		BillModel bm = billForm.getBillCardPanel().getBillModel();
		//用于按收费项目维度小计
		/*String pk_cost = maps_some.get(0).get("pk_costproject").toString();
		UFDouble cost_rec = new UFDouble(0);
		UFDouble cost_real = new UFDouble(0);
		UFDouble cost_qf = new UFDouble(0);*/
		//用于按客户维度小计
		String ht = maps_some.get(0).get("htcode").toString();
		UFDouble ht_rec = new UFDouble(0);
		UFDouble ht_real = new UFDouble(0);
		UFDouble ht_qf = new UFDouble(0);
		int addHt=0,addThe=0,row=0;
			
		for(int i=0;i<maps_some.size();i++){
			addHt=0;
			//addProj=0;
			addThe=0;
			Map<String,Object> map_some=maps_some.get(i);
			//if(map_some.get("pk_costproject").equals(pk_cost)){
				//收费项目相同
					if(map_some.get("htcode").equals(ht)){
						//客户名称相同
						ht_rec=ht_rec.add(new UFDouble (map_some.get("recmny").toString()));
						ht_real=ht_real.add(new UFDouble (map_some.get("realmny").toString()));
						ht_qf=ht_qf.add(new UFDouble( map_some.get("qfmny").toString()));
						/*cost_rec=cost_rec.add(new UFDouble (map_some.get("recmny").toString()));
						cost_real=cost_real.add(new UFDouble( map_some.get("realmny").toString()));
						cost_qf=cost_qf.add(new UFDouble (map_some.get("qfmny").toString()));*/
						//增加此行
						addThe=1;
					}else{
						//客户不同
						//增加客户小计行,重置客户名称和小计
						addHt=1;
						//增加项目小计行,重置项目名称和小计
						//addProj=1;
						//增加此行
						addThe=1;
					}
			/*}else{
				//收费项目不同
				if(map_some.get("pk_customer").equals(cust)){
					//客户相同
					cust_recc=cust_recc.add(new UFDouble (map_some.get("recmny").toString()));
					cust_c=cust_c.add(new UFDouble( map_some.get("realmny").toString()));
					cust_arr=cust_arr.add(new UFDouble (map_some.get("qfmny").toString()));
					//增加客户小计行,重置客户名称和小计
					addProj=1;
					//增加此行
					addThe=1;
				}else{
					//客户不同
					//增加客户小计行,重置客户名称和小计
					addCust=1;
					//增加项目小计行,重置项目名称和小计
					addProj=1;
					//增加此行
					addThe=1;
				}
			}*/
			/*if(addProj==1){
				bm.addLine();
				bm.setValueAt("收费项目小计", row, "htcode");
				bm.setValueAt(cost_rec, row, "recmny");
				bm.setValueAt(cost_real, row, "realmny");
				bm.setValueAt(cost_qf, row, "qfmny");
				for(int k=bm.getItemIndex("pk_org");k<bm.getColumnCount();k++){
					bm.setBackground(new Color(255, 238, 188),row, k);
				}
				row++;
				pk_cost=(String) map_some.get("pk_costproject");
				cost_rec=new UFDouble(map_some.get("recmny").toString());
				cost_real=new UFDouble(map_some.get("realmny").toString());
				cost_qf=new UFDouble(map_some.get("qfmny").toString());
			}*/
			if(addHt==1){
				bm.addLine();
				bm.setValueAt("合同小计", row, "htcode");
				bm.setValueAt(ht_rec, row, "recmny");
				bm.setValueAt(ht_real, row, "realmny");
				bm.setValueAt(ht_qf, row, "qfmny");
				for(int k=bm.getItemIndex("htcode");k<bm.getColumnCount();k++){
					bm.setBackground(new Color(255, 238, 188),row, k);
				}
				row++;
				ht=(String) map_some.get("htcode");
				ht_rec=new UFDouble(map_some.get("recmny").toString());
				ht_real=new UFDouble(map_some.get("realmny").toString());
				ht_qf=new UFDouble(map_some.get("qfmny").toString());
			}
			if(addThe==1){
				bm.addLine();
				bm.setValueAt(map_some.get("pk_org"), row, "pk_org");
				bm.setValueAt(map_some.get("pk_project"), row, "pk_project");
				bm.setValueAt(map_some.get("htcode"), row, "htcode");
				bm.setValueAt(map_some.get("pk_customer"), row, "pk_customer");
				bm.setValueAt(map_some.get("pk_house"), row, "pk_house");
				bm.setValueAt(map_some.get("recdate"), row, "recdate");
				bm.setValueAt(map_some.get("pk_costproject"), row, "pk_costproject");
				bm.setValueAt(map_some.get("recmny"), row, "recmny");
				bm.setValueAt(map_some.get("realmny"), row, "realmny");
				bm.setValueAt(map_some.get("qfmny"), row, "qfmny");
				row++;
			}
			//若是最后一行 则增加客户小计行和项目小计行
			if(i==maps_some.size()-1){
				/*bm.addLine();
				bm.setValueAt("收费项目小计", row, "htcode");
				bm.setValueAt(cost_rec, row, "recmny");
				bm.setValueAt(cost_real, row, "realmny");
				bm.setValueAt(cost_qf, row, "qfmny");
				for(int k=bm.getItemIndex("pk_org");k<bm.getColumnCount();k++){
					bm.setBackground(new Color(255, 238, 188),row, k);
				}
				row++;*/
				bm.addLine();
				bm.setValueAt("合同小计", row, "htcode");
				bm.setValueAt(ht_rec, row, "recmny");
				bm.setValueAt(ht_real, row, "realmny");
				bm.setValueAt(ht_qf, row, "qfmny");
				for(int k=bm.getItemIndex("htcode");k<bm.getColumnCount();k++){
					bm.setBackground(new Color(255, 238, 188),row, k);
				}
			}
		}
		
		if(bm.getRowCount()>0){
			bm.setValueAt(cond1, 0, "condition");
		}
	}
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
}