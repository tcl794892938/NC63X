package nc.ui.zl.ld_carcontract.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.ld_carcontract.ace.config.CalculateRentUtils;


import nc.vo.pub.bill.BillTabVO;

public class BusinessAction extends NCAction {

	private ShowUpableBillForm billForm;
	private AbstractAppModel model;
	public BusinessAction(){
		super();
		this.setCode("costAction");
		this.setBtnName("����ҵ����");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		//���ҵ��ҳǩ
		BillModel modela=panel.getBillModel("pk_carcontract_b");
		int row=modela.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "��ʾ", "�޳������ݣ���������ҵ���֣�");
			return ;
		}
		
		BillModel modelb=panel.getBillModel("pk_carcontract_c");
		int row2=modelb.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "��ʾ", "�������ҵ�������ݣ��Ƿ��������ɣ�");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		modelb.clearBodyData();
		CalculateRentUtils.recalSplitYwcfData(panel);//ҵ��������
		
		BillTabVO vo=panel.getBillModel("pk_carcontract_c").getTabvo();
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
	public AbstractAppModel getModel() {
		return model;
	}
	public void setModel(AbstractAppModel model) {
		this.model = model;
	}
	
}
