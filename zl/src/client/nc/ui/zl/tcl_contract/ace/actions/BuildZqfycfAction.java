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
import nc.vo.pub.lang.UFDate;

public class BuildZqfycfAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildZqfycfAction(){
		this.setCode("buildzqfycf");
		this.setBtnName("生成周期费用拆分");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		//周期费用拆分生成
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		
		//检测周期费用页签
		BillModel model=panel.getBillModel("pk_contract_zqfy");
		int row=model.getRowCount();
		if(row<=0){
			MessageDialog.showHintDlg(billForm, "提示", "无周期费用数据，不能生成周期费用拆分！");
			return ;
		}
		
		//校验日期是否准确
		BillModel modelcf=panel.getBillModel("pk_contract_zqmx");
		if(modelcf.getRowCount()>0){
			
			Object obj=modelcf.getValueAt(0, "dstartdate");
			UFDate udj=new UFDate(obj.toString());
			for(int i=0;i<row;i++){
				
				Object obj2=model.getValueAt(0, "dstartdate");
				UFDate ud2=new UFDate(obj2.toString());
				if(!udj.afterDate(ud2)){
					MessageDialog.showHintDlg(billForm, "提示", "周期付款变更日期需大于周期费用开始日期！");
					return ;
				}
			}
		}
				
		
		BillModel model2=panel.getBillModel("pk_contract_zqfycf");
		int row2=model2.getRowCount();
		if(row2>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有周期费用拆分数据，是否重新生成？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		
		model2.clearBodyData();
		
		CalculateRentUtils.recalSplitZqfyDataNew(panel);//周期费用拆分重算
		
		BillTabVO vo=panel.getBillModel("pk_contract_zqfycf").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("生成周期费用拆分成功！",billForm.getModel().getContext());
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
