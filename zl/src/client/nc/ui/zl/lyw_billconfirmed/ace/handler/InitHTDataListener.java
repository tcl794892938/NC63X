package nc.ui.zl.lyw_billconfirmed.ace.handler;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends DefaultFuncNodeInitDataListener {

	
	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;
	@Override
	public void initData(FuncletInitData data) {

		billForm.getBillCardPanel().getBillTable().removeSortListener();
		listView.getBillListPanel().getBodyTable().removeSortListener();
		billForm.getBillCardPanel().setBodyAutoAddLine(false);
		billForm.getBillCardPanel().setBodyMenuShow(false);
		billForm.getBillCardPanel().setTatolRowShow(true);
		
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
