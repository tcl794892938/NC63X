package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;
import nc.vo.pub.bill.BillTabVO;

public class BuildZzqAction extends NCAction {

	private static final long serialVersionUID = -7036613864808146217L;
	
	public BuildZzqAction(){
		this.setCode("buildxxq");
		this.setBtnName("增长期重算");
	}
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		int row=panel.getBillModel("pk_contract_zzq").getRowCount();
		if(row>0){
			int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检测已有增长期数据，是否确认重算？");
			if(it!=UIDialog.ID_OK){
				return ;
			}
		}
		
		//重算增长期
		int[] rows=new int[row];
		for(int i=0;i<row;i++){
			rows[i]=i;
		}
		CalculateRentUtils.recalZzqData(panel, rows);
		
		BillTabVO vo=panel.getBillModel("pk_contract_zzq").getTabvo();
		int tabIndex=panel.getTabbedPane(IBillItem.BODY).getIndexofTableCode(vo);
		panel.getTabbedPane(IBillItem.BODY).setSelectedIndex(tabIndex);
		
		ShowStatusBarMsgUtil.showStatusBarMsg("增长期重算成功！",billForm.getModel().getContext());
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
