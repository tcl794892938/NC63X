package nc.ui.zl.lyw_billconfirmed.ace.actions;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pub.lang.UFDouble;
import nc.ui.pubapp.uif2app.view.BillForm;

public class SaveAction extends SaveScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8162411324307023669L;
	
	public BillForm billForm;

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(ActionEvent e) throws Exception {
		billForm.getBillCardPanel().stopEditing();
		billForm.getBillCardPanel().dataNotNullValidate();
		
		BillModel model=billForm.getBillCardPanel().getBillModel();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		for(int i=0;i<model.getRowCount();i++){
			if(getuf(model.getValueAt(i, "ntaxrate"))!=new UFDouble(0)){
				if(getuf(model.getValueAt(i, "ntaxmny")).add(getuf(model.getValueAt(i, "nnotaxmny"))).
						compareTo(getuf(model.getValueAt(i, "amountconfirming")))!=0){
					//MessageDialog.showHintDlg(billForm, "提示", "存在税额+无税金额与本次确认金额不等，不可保存！");
					throw new Exception("存在税额+无税金额与本次确认金额不等，不可保存！");
				}
			}
			
			Object pk=getStgObj(model.getValueAt(i, "vsrcid"));
			//获取确认总金额
			String sql_conm = "select sum(b.amountconfirming) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+pk+"' and pk_billconfirmedb <> '"+getStgObj(model.getValueAt(i, "pk_billconfirmedb"))+"'";
			Object obj1 = iQ.executeQuery(sql_conm, new ColumnProcessor());
			UFDouble UF_obj1 = getuf(obj1).add(getuf(model.getValueAt(i, "amountconfirming")));
			//获取总税额
			String sql_tax = "select sum(b.ntaxmny) from zl_billconfirmedb b where nvl(dr,0)=0 and b.vsrcid='"+pk+"' and pk_billconfirmedb <> '"+getStgObj(model.getValueAt(i, "pk_billconfirmedb"))+"'";
			Object obj2 = iQ.executeQuery(sql_tax, new ColumnProcessor());
			UFDouble UF_obj2 = getuf(obj2).add(getuf(model.getValueAt(i, "ntaxmny")));
			//获取总无税金额
			UFDouble UF_obj3 = getuf(model.getValueAt(i, "amountconfirmed")).add(getuf(model.getValueAt(i, "nnotaxmny")));
			
			if(getuf(model.getValueAt(i, "amountreceivable")).sub(UF_obj1)==new UFDouble(0)){
				//获取对应带收入确认单的无税金额、税额
				String sql_a = "select nnotaxmny,ntaxmny from zl_confirmation c where nvl(dr,0)=0 and c.pk_confirmation='"+pk+"'";
				Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql_a, new MapProcessor());
				UFDouble nn = new UFDouble(0);
				UFDouble nt = new UFDouble(0);
				nn = nn.add(getuf(map.get("nnotaxmny")), 2, 0);
				nt = nt.add(getuf(map.get("ntaxmny")), 2, 0);
				if(nn.compareTo(UF_obj3)!=0||nt.compareTo(UF_obj2)!=0){
					MessageDialog.showHintDlg(billForm, "提示", "存在税额或无税金额与上游金额不等，不可保存！");
					return;
				}
			}
			
		}
		

		super.doAction(e);
	}
	
	public UFDouble getuf(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
}
