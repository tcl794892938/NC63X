package nc.ui.zl.ld_carcontract.ace.handler;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent> {
	
	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		e.setReturnValue(true);
		
		String tabcode=e.getBillCardPanel().getCurrentBodyTableCode();
		
		if(e.getKey().equals("ndiscountmoney")&&tabcode.equals("pk_carcontract_c")){
			int row = billForm.getBillCardPanel().getBillModel("pk_carcontract_f").getRowCount();
			BillModel model = billForm.getBillCardPanel().getBillModel("pk_carcontract_f");
			if(row>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "检查已有财务拆分数据，是否重新生成？");
				if(it!=UIDialog.ID_OK){
					return ;
				}
			}
			model.clearBodyData();
		}
		
		if(e.getKey().equals("platenum")&&tabcode.equals("pk_carcontract_b")){
			Object pk_customer = billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			UIRefPane ref=(UIRefPane)e.getBillCardPanel().getBodyItem("platenum").getComponent();
			ref.getRefModel().addWherePart(" and khname='"+pk_customer+"'");
		}
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

}
