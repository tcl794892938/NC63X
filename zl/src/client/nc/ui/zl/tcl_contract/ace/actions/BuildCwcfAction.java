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

public class BuildCwcfAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildCwcfAction(){
		this.setCode("buildcwcf");
		this.setBtnName("生成财务拆分");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//拆分生成
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		//检测业务页签
		BillModel model=panel.getBillModel("pk_contract_ywcf");
		int row=model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "提示", "无业务拆分数据，不能生成财务拆分！");
			return ;
		}
		
		BillModel model2=panel.getBillModel("pk_contract_cwcf");
		int row2=model2.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有财务拆分数据，是否重新生成？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		model2.clearBodyData();
		
		CalculateRentUtils.recalSplitCwcfData(panel);//财务拆分重算
		
		BillTabVO vo=panel.getBillModel("pk_contract_cwcf").getTabvo();
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

}
