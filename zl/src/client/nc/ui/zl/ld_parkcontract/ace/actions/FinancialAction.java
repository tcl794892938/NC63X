package nc.ui.zl.ld_parkcontract.ace.actions;

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
import nc.ui.zl.ld_parkcontract.ace.config.CalculateRentUtils;

import nc.vo.pub.bill.BillTabVO;

public class FinancialAction extends NCAction {

	private ShowUpableBillForm billForm;
	private AbstractAppModel model;
	public FinancialAction(){
		super();
		this.setCode("financialAction");
		this.setBtnName("财务拆分");
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		BillModel modela=panel.getBillModel("pk_parkcontract_c");
		int row=modela.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "提示", "无业务拆分数据，不能生成财务拆分！");
			return ;
		}
		
		BillModel modelc=panel.getBillModel("pk_parkcontract_f");
		int row2=modelc.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有财务拆分数据，是否重新生成？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
	   modelc.clearBodyData();
		
		CalculateRentUtils.recalSplitZqfyData(panel);
		
		BillTabVO vo=panel.getBillModel("pk_parkcontract_f").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("生成财务拆分成功！",billForm.getModel().getContext());
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
