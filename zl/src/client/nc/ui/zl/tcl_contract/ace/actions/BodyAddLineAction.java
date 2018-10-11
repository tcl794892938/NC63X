package nc.ui.zl.tcl_contract.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.ValidationException;

public class BodyAddLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	private static final long serialVersionUID = -3544044286103455949L;
	
	private ShowUpableBillForm billform;

	@Override
	public void doAction() {
		
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "提示", e.getMessage());
			return ;
		}
		
		String tabcode=billform.getBillCardPanel().getCurrentBodyTableCode();
		
		if(tabcode.equals("pk_contract_house")){//校验客户是否维护
			BillModel model_c=billform.getBillCardPanel().getBillModel("pk_contract_cust");
			Object obj=model_c.getValueAt(0, "pk_customer");
			if(obj==null){
				MessageDialog.showHintDlg(billform, "提示", "请先维护客户信息！");
				return ;
			}
		}
		
		if(tabcode.equals("pk_contract_fkmx")){
			BillModel model_c=billform.getBillCardPanel().getBillModel(tabcode);
			if(model_c.getRowCount()>0){
				MessageDialog.showHintDlg(billform, "提示", "付款方式支持变更一次，不能再次增行！");
				return ;
			}
		}
		
		if(tabcode.equals("pk_contract_zzq")){
			BillModel model_c=billform.getBillCardPanel().getBillModel("pk_contract_house");
			if(model_c.getRowCount()<=0){
				MessageDialog.showHintDlg(billform, "提示", "请先录入房产信息！");
				return ;
			}
		}
		
		super.doAction();
		
		
		if(tabcode.equals("pk_contract_zqfy")){
			
			Object obj=billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			Object objdate=billform.getBillCardPanel().getHeadItem("dstartdate").getValueObject();
			int row=billform.getBillCardPanel().getBillTable(tabcode).getSelectedRow();
			billform.getBillCardPanel().getBillModel(tabcode).setValueAt(obj, row, "pk_customer");
			billform.getBillCardPanel().getBillModel(tabcode).setValueAt(objdate, row, "dstartdate");
			
//			Object obj2=billform.getBillCardPanel().getHeadItem("taxstyle").getValueObject();
//			Integer it=0;
//			if(obj2!=null){
//				it=Integer.valueOf(obj2.toString());
//			}
			billform.getBillCardPanel().getBillModel(tabcode).setValueAt(6, row, "ntaxrate");
			
			billform.getBillCardPanel().getBillModel(tabcode).loadLoadRelationItemValue();
		}
		
		if(tabcode.equals("pk_contract_house")){
			
			Object obj=billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			int row=billform.getBillCardPanel().getBillTable(tabcode).getSelectedRow();
			billform.getBillCardPanel().getBillModel(tabcode).setValueAt(obj, row, "pk_customer");
			
			billform.getBillCardPanel().getBillModel(tabcode).loadLoadRelationItemValue();
		}
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
}
