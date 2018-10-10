package nc.ui.zl.ld_report_cwdy.ace.actions;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener{

	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;

	@Override
	public void initData(FuncletInitData data) {
		
		billForm.getBillCardPanel().getBillTable("report_cwdy").removeSortListener();
		listView.getBillListPanel().getBodyTable("report_cwdy").removeSortListener();
		billForm.getBillCardPanel().setBodyAutoAddLine("report_cwdy", false);
		billForm.getBillCardPanel().getBodyPanel("report_cwdy").setBBodyMenuShow(false);
		
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
