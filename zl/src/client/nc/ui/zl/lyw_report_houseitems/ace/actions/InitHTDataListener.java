package nc.ui.zl.lyw_report_houseitems.ace.actions;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class InitHTDataListener extends nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener{

	private ShowUpableBillForm billForm;
	


	@Override
	public void initData(FuncletInitData data) {
		
		billForm.getBillCardPanel().getBillTable("houseitems").removeSortListener();
		
		billForm.getBillCardPanel().setBodyAutoAddLine("houseitems", false);
		billForm.getBillCardPanel().getBodyPanel("houseitems").setBBodyMenuShow(false);
		
		super.initData(data);
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	
}
