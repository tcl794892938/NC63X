package nc.ui.zl.lyw_report_customeritems.ace.actions;

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
				ShowStatusBarMsgUtil.showStatusBarMsg("δ�鵽�κ���Ϣ��", getModel().getContext());
				return ;
			}
			hmodel.execLoadFormula();
			if(hmodel.getRowCount()>0){
				UFDouble yszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("nreccollecttotal"));
				UFDouble sszj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("ncollecttotal"));
				UFDouble qfzj=(UFDouble) billForm.getBillCardPanel().getTotalTableModel().getValueAt(0, hmodel.getItemIndex("narrearagetotal"));
				Double num1=new BigDecimal(yszj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num2=new BigDecimal(sszj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double num4=new BigDecimal(qfzj.doubleValue()/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				//�ܼ�
				hmodel.addLine();
				hmodel.setValueAt("�ܼ�", hmodel.getRowCount()-1, "pk_org");
				hmodel.setValueAt(num1, hmodel.getRowCount()-1, "nreccollecttotal");
				hmodel.setValueAt(num2, hmodel.getRowCount()-1, "ncollecttotal");
				hmodel.setValueAt(num4, hmodel.getRowCount()-1, "narrearagetotal");
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("pk_org"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("pk_project"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("pk_customer"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("pk_costproject"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("pk_house"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("nreccollecttotal"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("ncollecttotal"));
				hmodel.setBackground(new Color(255, 255, 188),hmodel.getRowCount()-1, hmodel.getItemIndex("narrearagetotal"));
				billForm.getBillCardPanel().setTatolRowShow(false);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processQuery2() throws Exception {
		IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		//����where����
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
		
		//һ��Ӧ������
		if(where.indexOf("AND customeritems.rdate")!=-1){
			jie = where.substring(where.indexOf("AND customeritems.rdate")+4,where.indexOf("AND customeritems.rdate")+48);
			jie1 = jie;
			jie = jie.replaceAll("customeritems.rdate", "gatherdate");
			where = where.replaceAll("AND "+jie1, "");
		}else if(where.indexOf("(customeritems.rdate")!=-1){//2��Ӧ������
			jie = where.substring(where.indexOf("(customeritems.rdate"),where.indexOf("and customeritems.rdate")+49);
			jie1 = " \\" +jie.substring(0, jie.length()-1)+"\\" +jie.substring(jie.length()-1,jie.length());
			
			jie = jie.replaceAll("customeritems.rdate", "gatherdate");
			where = where.replaceAll("AND"+jie1, "");
		}
		if(!jie.equals("")){
			jie3 = jie;
		}
		
		where=where.replaceAll("customeritems.", "m.");
		
		String sql_someByHouse="select * from (select x.pk_org,x.name,x.pk_project,x.pk_customer,x.customername,x.pk_costproject,x.pk_house," +
				"sum(x.nreccollecttotal) nreccollecttotal,sum(x.ncollecttotal) ncollecttotal," +
				"sum(x.nreccollecttotal)-sum(x.ncollecttotal) narrearagetotal from " +
				"(select a.pk_org,a.name,s.pk_project,a.pk_customer,a.customername,s.pk_costproject,s.pk_house," +
				"(case when s.ys is null then 0 else s.ys end) nreccollecttotal," +
				"(case when s.ss is null then 0 else s.ss end) ncollecttotal from " +
				"(select o.pk_org,o.name,c.pk_customer,c.customername from org_orgs o left join zl_customer c on o.pk_org=c.pk_org " +
				"where nvl(o.dr,0)=0 and nvl(c.dr,0)=0) a left join " +
				"((select r.pk_project,r.pk_customer,r.pk_costproject,r.pk_house,r.nrecmoney ys,r.nrealmoney ss,r.gatherdate from zl_recbill r " +
				"where nvl(r.dr,0)=0 and r.vbillstatus=1 and "+jie3+") " +
				"union all " +
				"(select g.pk_project,g.pk_customer,gb.pk_costproject,gb.pk_house,0,gb.nskmny,null from zl_gather g " +
				"left join zl_gather_b gb on g.pk_gather=gb.pk_gather where nvl(g.dr,0)=0 and nvl(gb.dr,0)=0 and g.vbillstatus=1 and g.isadd='Y')" +
				") s on a.pk_customer=s.pk_customer) x"+
				" group by x.pk_org,x.name,x.pk_project,x.pk_customer,x.customername,x.pk_costproject,x.pk_house having x.pk_project is not null " +
				"order by x.pk_org,x.name,x.pk_project,x.pk_customer,x.customername,x.pk_house) m where "+where;
				
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<Map<String , Object>> maps_some=(List<Map<String , Object>>)iQ.executeQuery(sql_someByHouse, new MapListProcessor());
		if(maps_some==null||maps_some.size()<=0){
			ShowStatusBarMsgUtil.showStatusBarMsg("δ�鵽�κ���Ϣ��", getModel().getContext());
			return ;
		}
		BillModel bm = billForm.getBillCardPanel().getBillModel();
			//���ڰ��ͻ�ά��С��
			String cust = maps_some.get(0).get("pk_customer").toString();
			UFDouble cust_recc = new UFDouble(0);
			UFDouble cust_c = new UFDouble(0);
			UFDouble cust_arr = new UFDouble(0);
			//���ڰ���Ŀά��С��
			String pk_proj = maps_some.get(0).get("pk_project").toString();
			UFDouble proj_recc = new UFDouble(0);
			UFDouble proj_c = new UFDouble(0);
			UFDouble proj_arr = new UFDouble(0);
			int addCust=0,addProj=0,addThe=0,row=0;
			//�����һ��
			//bm.execLoadFormula();
			/*bm.addLine();
			bm.setValueAt(maps_some.get(0).get("pk_org"), row, "pk_orgpk");
			bm.setValueAt(maps_some.get(0).get("pk_project"), row, "pk_project");
			bm.setValueAt(maps_some.get(0).get("name"), row, "pk_org");
			bm.setValueAt(maps_some.get(0).get("customername"), row, "pk_customer");
			bm.setValueAt(maps_some.get(0).get("pk_customer"), row, "pk_customerpk");
			bm.setValueAt(maps_some.get(0).get("pk_costproject"), row, "pk_costproject");
			bm.setValueAt(maps_some.get(0).get("pk_house"), row, "pk_house");
			bm.setValueAt(maps_some.get(0).get("pk_house"), row, "pk_housepk");
			bm.setValueAt(maps_some.get(0).get("nreccollecttotal"), row, "nreccollecttotal");
			bm.setValueAt(maps_some.get(0).get("ncollecttotal"), row, "ncollecttotal");
			bm.setValueAt(maps_some.get(0).get("narrearagetotal"), row, "narrearagetotal");
			row++;*/
			for(int i=0;i<maps_some.size();i++){
				addCust=0;
				addProj=0;
				addThe=0;
				Map<String,Object> map_some=maps_some.get(i);
				if(map_some.get("pk_customer")==null){
					System.out.println(i);
					System.out.println(maps_some.size());
				}
				if(map_some.get("pk_customer").equals(cust)){
				//�ͻ�������ͬ
					if(map_some.get("pk_project").equals(pk_proj)){
					//��Ŀ��ͬ
						//ͳ�ƿͻ� ��Ŀ
						cust_recc=cust_recc.add(new UFDouble (map_some.get("nreccollecttotal").toString()));
						cust_c=cust_c.add(new UFDouble (map_some.get("ncollecttotal").toString()));
						cust_arr=cust_arr.add(new UFDouble( map_some.get("narrearagetotal").toString()));
						proj_recc=proj_recc.add(new UFDouble (map_some.get("nreccollecttotal").toString()));
						proj_c=proj_c.add(new UFDouble( map_some.get("ncollecttotal").toString()));
						proj_arr=proj_arr.add(new UFDouble (map_some.get("narrearagetotal").toString()));
						//���Ӵ���
						addThe=1;
					}else{
					//��Ŀ��ͬ
						//���ӿͻ�С����,���ÿͻ����ƺ�С��
						addCust=1;
						//������ĿС����,������Ŀ���ƺ�С��
						addProj=1;
						//���Ӵ���
						addThe=1;
					}
				}else{
				//�ͻ����Ʋ�ͬ
					if(map_some.get("pk_project").equals(pk_proj)){
					//��Ŀ��ͬ
						//ͳ����Ŀ
						proj_recc=proj_recc.add(new UFDouble (map_some.get("nreccollecttotal").toString()));
						proj_c=proj_c.add(new UFDouble( map_some.get("ncollecttotal").toString()));
						proj_arr=proj_arr.add(new UFDouble (map_some.get("narrearagetotal").toString()));
						//���ӿͻ�С����,���ÿͻ����ƺ�С��
						addCust=1;
						//���Ӵ���
						addThe=1;
					}else{
					//��Ŀ��ͬ
						//���ӿͻ�С����,���ÿͻ����ƺ�С��
						addCust=1;
						//������ĿС����,������Ŀ���ƺ�С��
						addProj=1;
						//���Ӵ���
						addThe=1;
					}
				}
				if(addCust==1){
					bm.addLine();
					bm.setValueAt("�ͻ�С��", row, "pk_customer");
					bm.setValueAt(cust_recc, row, "nreccollecttotal");
					bm.setValueAt(cust_c, row, "ncollecttotal");
					bm.setValueAt(cust_arr, row, "narrearagetotal");
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_customer"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_costproject"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_house"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("nreccollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("ncollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("narrearagetotal"));
					row++;
					cust=(String) map_some.get("pk_customer");
					cust_recc=new UFDouble(map_some.get("nreccollecttotal").toString());
					cust_c=new UFDouble(map_some.get("ncollecttotal").toString());
					cust_arr=new UFDouble(map_some.get("narrearagetotal").toString());
				}
				if(addProj==1){
					bm.addLine();
					bm.setValueAt("��ĿС��", row, "pk_org");
					bm.setValueAt(proj_recc, row, "nreccollecttotal");
					bm.setValueAt(proj_c, row, "ncollecttotal");
					bm.setValueAt(proj_arr, row, "narrearagetotal");
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_org"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_project"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_customer"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_costproject"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_house"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("nreccollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("ncollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("narrearagetotal"));
					row++;
					pk_proj=(String) map_some.get("pk_project");
					proj_recc=new UFDouble(map_some.get("nreccollecttotal").toString());
					proj_c=new UFDouble(map_some.get("ncollecttotal").toString());
					proj_arr=new UFDouble(map_some.get("narrearagetotal").toString());
				}
				if(addThe==1){
					bm.addLine();
					bm.setValueAt(map_some.get("pk_org"), row, "pk_orgpk");
					bm.setValueAt(map_some.get("pk_project"), row, "pk_project");
					bm.setValueAt(map_some.get("name"), row, "pk_org");
					bm.setValueAt(map_some.get("customername"), row, "pk_customer");
					bm.setValueAt(map_some.get("pk_customer"), row, "pk_customerpk");
					bm.setValueAt(map_some.get("pk_costproject"), row, "pk_costproject");
					bm.setValueAt(map_some.get("pk_house"), row, "pk_house");
					bm.setValueAt(map_some.get("pk_house"), row, "pk_housepk");
					bm.setValueAt(map_some.get("nreccollecttotal"), row, "nreccollecttotal");
					bm.setValueAt(map_some.get("ncollecttotal"), row, "ncollecttotal");
					bm.setValueAt(map_some.get("narrearagetotal"), row, "narrearagetotal");
					row++;
				}
				//�������һ�� �����ӿͻ�С���к���ĿС����
				if(i==maps_some.size()-1){
					bm.addLine();
					bm.setValueAt("�ͻ�С��", row, "pk_customer");
					bm.setValueAt(cust_recc, row, "nreccollecttotal");
					bm.setValueAt(cust_c, row, "ncollecttotal");
					bm.setValueAt(cust_arr, row, "narrearagetotal");
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_customer"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_costproject"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_house"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("nreccollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("ncollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("narrearagetotal"));
					row++;
					bm.addLine();
					bm.setValueAt("��ĿС��", row, "pk_org");
					bm.setValueAt(proj_recc, row, "nreccollecttotal");
					bm.setValueAt(proj_c, row, "ncollecttotal");
					bm.setValueAt(proj_arr, row, "narrearagetotal");
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_org"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_project"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_customer"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_costproject"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("pk_house"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("nreccollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("ncollecttotal"));
					bm.setBackground(new Color(255, 238, 188),row, bm.getItemIndex("narrearagetotal"));
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