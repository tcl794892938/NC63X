package nc.ui.zl.hql_throwalease.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.zl.tcl_contract.ContractVO;

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
		if(bill.getBillCardPanel().getBillTable("pk_throwalease_tzys").getRowCount() <= 0){
			//MessageDialog.showHintDlg(bill, "��ʾ", "�������յǼ�ҳǩ�в���Ϊ�գ�");
			throw new BusinessException("�������յǼ�ҳǩ�в���Ϊ�գ�");
			//return;
		}
		
		super.doAction(e);
	}
	
}
