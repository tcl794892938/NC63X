package nc.ui.zl.lm_customer.ace.handler;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends DefaultFuncNodeInitDataListener {

	
	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;
	@Override
	public void initData(FuncletInitData data) {

		String[] tabcode=new String[]{"pk_customerfcxx","pk_customerqjfy","pk_customerwxfw","pk_customercar"};
		for(String tab : tabcode){
			billForm.getBillCardPanel().getBillTable(tab).removeSortListener();
			listView.getBillListPanel().getBodyTable(tab).removeSortListener();
			billForm.getBillCardPanel().setBodyAutoAddLine(tab, false);
			billForm.getBillCardPanel().getBodyPanel(tab).setBBodyMenuShow(false);
		}
		billForm.getBillCardPanel().getBodyPanel("pk_customerzzxm").setBBodyMenuShow(true);
		super.initData(data);
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}


	public ShowUpableBillListView getListView() {
		return listView;
	}


	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}
	
	

}
