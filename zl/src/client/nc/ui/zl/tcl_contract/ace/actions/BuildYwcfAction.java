package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

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

public class BuildYwcfAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildYwcfAction(){
		this.setCode("buildywcf");
		this.setBtnName("����ҵ����");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		//�������
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		//���ҵ��ҳǩ
		BillModel model=panel.getBillModel("pk_contract_house");
		int row=model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "��ʾ", "�޷�����Ϣ���ݣ���������ҵ���֣�");
			return ;
		}
		
		BillModel model2=panel.getBillModel("pk_contract_ywcf");
		int row2=model2.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "�������ҵ�������ݣ��Ƿ��������ɣ�");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		model2.clearBodyData();
		
		CalculateRentUtils.recalSplitYwcfDataNew(panel);//ҵ��������
		
		BillTabVO vo=panel.getBillModel("pk_contract_ywcf").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("����ҵ���ֳɹ���",billForm.getModel().getContext());
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
