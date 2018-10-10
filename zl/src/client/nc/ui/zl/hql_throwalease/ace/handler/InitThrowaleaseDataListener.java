package nc.ui.zl.hql_throwalease.ace.handler;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitThrowaleaseDataListener extends DefaultFuncNodeInitDataListener {
	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;
	@Override
	public void initData(FuncletInitData data) {

		String[] tabcode = new String[]{"pk_throwalease_khfc","pk_throwalease_tzys",
				           "pk_throwalease_bzjth","pk_throwalease_fyqs","pk_throwalease_zqfyqs"};
		for(String tab : tabcode){
			billForm.getBillCardPanel().getBillTable(tab).removeSortListener();
			listView.getBillListPanel().getBodyTable(tab).removeSortListener();
			billForm.getBillCardPanel().setBodyAutoAddLine(tab, false);
			billForm.getBillCardPanel().getBodyPanel(tab).setBBodyMenuShow(false);
		}
		billForm.getBillCardPanel().setTatolRowShow("pk_throwalease_fyqs", true);
		billForm.getBillCardPanel().setTatolRowShow("pk_throwalease_zqfyqs", true);
		
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
