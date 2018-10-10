package nc.ui.zl.tcl_contract.ace.actions;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.ValidationException;

public class BodyInsLineAction extends BodyInsertLineAction {
	
	private static final long serialVersionUID = -6846853235792225857L;
	
	private ShowUpableBillForm billform;
	
	@Override
	public void doAction() {
		
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "��ʾ", e.getMessage());
			return ;
		}
		
		String tabcode=billform.getBillCardPanel().getCurrentBodyTableCode();
		
		if(tabcode.equals("pk_contract_house")){//У��ͻ��Ƿ�ά��
			BillModel model_c=billform.getBillCardPanel().getBillModel("pk_contract_cust");
			Object obj=model_c.getValueAt(0, "pk_customer");
			if(obj==null){
				MessageDialog.showHintDlg(billform, "��ʾ", "����ά���ͻ���Ϣ��");
				return ;
			}
		}
		
		super.doAction();
		
		if(tabcode.equals("pk_contract_zqfy")){
			
			Object obj=billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			int row=billform.getBillCardPanel().getBillTable(tabcode).getSelectedRow();
			billform.getBillCardPanel().getBillModel(tabcode).setValueAt(obj, row, "pk_customer");
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
