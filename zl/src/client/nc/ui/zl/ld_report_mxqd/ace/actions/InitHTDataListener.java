package nc.ui.zl.ld_report_mxqd.ace.actions;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener{

	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;

	@Override
	public void initData(FuncletInitData data) {
		
		billForm.getBillCardPanel().getBillTable("report_mxqd").removeSortListener();
		listView.getBillListPanel().getBodyTable("report_mxqd").removeSortListener();
		billForm.getBillCardPanel().setBodyAutoAddLine("report_mxqd", false);
		billForm.getBillCardPanel().getBodyPanel("report_mxqd").setBBodyMenuShow(false);
		
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
