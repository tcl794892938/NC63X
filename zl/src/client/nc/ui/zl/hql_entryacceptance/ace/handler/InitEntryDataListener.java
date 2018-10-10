package nc.ui.zl.hql_entryacceptance.ace.handler;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitEntryDataListener extends DefaultFuncNodeInitDataListener {
	private ShowUpableBillForm billForm;
	
	private ShowUpableBillListView listView;
	@Override
	public void initData(FuncletInitData data) {

		String[] tabcode=new String[]{"pk_entryacceptance_khfc","pk_entryacceptance_jcys"};
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
