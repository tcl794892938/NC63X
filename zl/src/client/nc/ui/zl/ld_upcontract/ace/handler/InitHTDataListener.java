package nc.ui.zl.ld_upcontract.ace.handler;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener{

	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;

	@Override
	public void initData(FuncletInitData data) {
		String[] tabcode=new String[]{"pk_upcontract_u"};
		for(String tab : tabcode){
			billForm.getBillCardPanel().getBillTable(tab).removeSortListener();
			listView.getBillListPanel().getBodyTable(tab).removeSortListener();
			billForm.getBillCardPanel().setBodyAutoAddLine(tab, false);
			billForm.getBillCardPanel().getBodyPanel(tab).setBBodyMenuShow(false);
		}
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
