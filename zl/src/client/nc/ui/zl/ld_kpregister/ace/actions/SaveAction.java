package nc.ui.zl.ld_kpregister.ace.actions;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDouble;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		
		//校验开票号重复
		Object pkobj=billform.getBillCardPanel().getHeadItem("pk_kpregister").getValueObject();
		Object code=billform.getBillCardPanel().getHeadItem("kpcode").getValueObject();
		String sql1="select count(1) from zl_kpregister where nvl(dr,0)=0 and vbilltypecode='H650' and " +
				" kpcode='"+code+"' and pk_kpregister<>'"+pkobj+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql1, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(billform, "提示", "开票号重复！");
			return ;
		}
		
		
		
		
		BillModel model=billform.getBillCardPanel().getBillModel();
		
		for(int i=0;i<model.getRowCount();i++){
			if(getuf(model.getValueAt(i, "ntaxrate"))!=new UFDouble(0)){
				if(getuf(model.getValueAt(i, "ntaxmny")).add(getuf(model.getValueAt(i, "nnotaxmny"))).
						compareTo(getuf(model.getValueAt(i, "kpmoney")))!=0){
					//MessageDialog.showHintDlg(billForm, "提示", "存在税额+无税金额与本次确认金额不等，不可保存！");
					throw new Exception("存在税额+无税金额与本次确认金额不等，不可保存！");
				}
			}
			
			Object pk=getStgObj(model.getValueAt(i, "vsrcid"));
			//获取确认总金额
			String sql_conm = "select sum(d.kpmoney) from zl_kpregister_d d where nvl(d.dr,0)=0 and d.vsrcid='"+pk+"' and exists(select 1 from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=d.pk_kpregister and k.vbillstatus=1) and pk_kpregister_d <> '"+getStgObj(model.getValueAt(i, "pk_kpregister_d"))+"'";
			Object obj1 = iQ.executeQuery(sql_conm, new ColumnProcessor());
			UFDouble UF_obj1 = getuf(obj1).add(getuf(model.getValueAt(i, "kpmoney")));
			//获取总税额
			String sql_tax = "select sum(d.ntaxmny) from zl_kpregister_d d where nvl(d.dr,0)=0 and d.vsrcid='"+pk+"' and exists(select 1 from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=d.pk_kpregister and k.vbillstatus=1) and pk_kpregister_d <> '"+getStgObj(model.getValueAt(i, "pk_kpregister_d"))+"'";
			Object obj2 = iQ.executeQuery(sql_tax, new ColumnProcessor());
			UFDouble UF_obj2 = getuf(obj2).add(getuf(model.getValueAt(i, "ntaxmny")));
			//获取总无税金额
			String sql_notax = "select sum(d.nnotaxmny) from zl_kpregister_d d where nvl(d.dr,0)=0 and d.vsrcid='"+pk+"' and exists(select 1 from zl_kpregister k where nvl(k.dr,0)=0 and k.pk_kpregister=d.pk_kpregister and k.vbillstatus=1) and pk_kpregister_d <> '"+getStgObj(model.getValueAt(i, "pk_kpregister_d"))+"'";
			Object obj3 = iQ.executeQuery(sql_notax, new ColumnProcessor());
			UFDouble UF_obj3 = getuf(obj3).add(getuf(model.getValueAt(i, "nnotaxmny")));
			
			if((getuf(model.getValueAt(i, "nrecmoney")).sub(UF_obj1)).compareTo(new UFDouble(0))==0){
				//获取对应 应收单的无税金额、税额
				String sql_a = "select nnotaxmoney,ntaxmoney from zl_recbill  where nvl(dr,0)=0 and pk_recbill='"+pk+"'";
				Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql_a, new MapProcessor());
				UFDouble nn = new UFDouble(0);
				UFDouble nt = new UFDouble(0);
				nn = nn.add(getuf(map.get("nnotaxmoney")), 2, 0);
				nt = nt.add(getuf(map.get("ntaxmoney")), 2, 0);
				if(nn.compareTo(UF_obj3)!=0||nt.compareTo(UF_obj2)!=0){
					MessageDialog.showHintDlg(billform, "提示", "存在税额或无税金额与上游金额不等，不可保存！");
					return;
				}
			}
			
		}
		
		
		
		super.doAction(e);
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
