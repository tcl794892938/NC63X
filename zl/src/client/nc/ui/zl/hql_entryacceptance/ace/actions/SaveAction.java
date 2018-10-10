package nc.ui.zl.hql_entryacceptance.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class SaveAction extends SaveScriptAction {
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		if(bill.getBillCardPanel().getBillTable("pk_entryacceptance_jcys").getRowCount() <= 0){
			//MessageDialog.showHintDlg(bill, "��ʾ", "�������յǼ�ҳǩ�в���Ϊ�գ�");
			throw new BusinessException("�������յǼ�ҳǩ�в���Ϊ�գ�");
			
		}
		super.doAction(e);
	}
	
}
