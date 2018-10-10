package nc.ui.zl.ld_parkcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.IShowMsgConstant;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.zl.ld_parkcontract.ace.config.CalculateRentUtils;

import nc.vo.pub.bill.BillTabVO;

public class BusinessAction extends NCAction {

	private ShowUpableBillForm billForm;
	private AbstractAppModel model;
	public BusinessAction(){
		super();
		this.setCode("costAction");
		this.setBtnName("业务拆分");
		
		//ActionInitializer.initializeAction(this, IActionCode.EDIT); 
	}
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		//检测业务页签
		BillModel modela=panel.getBillModel("pk_parkcontract_b");
		int row=modela.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "提示", "无车位数据，不能生成业务拆分！");
			return ;
		}
		
		BillModel modelb=panel.getBillModel("pk_parkcontract_c");
		int row2=modelb.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有业务拆分数据，是否重新生成？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		modelb.clearBodyData();
		CalculateRentUtils.recalSplitYwcfData(panel);//业务拆分重算
		
		BillTabVO vo=panel.getBillModel("pk_parkcontract_c").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("生成业务拆分成功！",billForm.getModel().getContext());
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
