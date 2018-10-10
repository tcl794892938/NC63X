package nc.ui.zl.cwf_salescontrol.ace.handler;

import nc.bs.uif2.IActionCode;
import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;

public class FuncNodeInitDataListener extends DefaultFuncNodeInitDataListener{

	private AbstractUIAppModel model;

	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}
	
	public ShowUpableBillForm billForm;

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	

	@Override
	public void initData(FuncletInitData data) {
		ActionInitializer.initializeAction( new AddAction(), IActionCode.ADD);
		//model.setOtherUiState(UIState.NOT_EDIT);
		model.setUiState(UIState.ADD);
		this.billForm.getBillCardPanel().setBodyMultiSelect(false);
		this.billForm.getBillCardPanel().setRowNOShow(false);
		getModel().fireEvent(new AppEvent(AppEventConst.MODEL_INITIALIZED, this, null));
		super.initData(data);
		billForm.getBillCardPanel().getBillTable().removeSortListener();
	}
}
