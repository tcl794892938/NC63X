package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;
import nc.vo.pub.bill.BillTabVO;

public class BuildHeadAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildHeadAction(){
		this.setCode("buildhead");
		this.setBtnName("�������");
		this.putValue(Action.SHORT_DESCRIPTION, "���㷿�����");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//���ݱ���ķ���ҳǩ���ܱ�ͷ��������
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		CalculateRentUtils.recalRent(panel);//�������
		
		BillModel model2=panel.getBillModel("pk_contract_zjmx");
		int row2=model2.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "������������ϸ���ݣ��Ƿ��������ɣ�");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		model2.clearBodyData();
		CalculateRentUtils.recalRent2(panel);//�������
		
		BillTabVO vo=panel.getBillModel("pk_contract_zjmx").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("��ͷ�������ɹ���",billForm.getModel().getContext());
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
