package nc.ui.zl.hql_customertype_org.ace.handler;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AppEventConst;

public class ShowBillForm extends ShowUpableBillForm {
	private Object currentSelectObj;

	@Override
	public void handleEvent(AppEvent event) {
		// TODO 自动生成的方法存根
		if (AppEventConst.SELECTION_CHANGED == event.getType()&& this.getModel().getUiState() == UIState.NOT_EDIT 
				&&this.currentSelectObj!=this.getModel().getSelectedData()) {
			
			this.currentSelectObj=this.getModel().getSelectedData();
			new SelectEventHandler().SelectEvent(event,this);
		}
		super.handleEvent(event);
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
	}
	
}
