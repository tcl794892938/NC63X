package nc.ui.zl.ly_report.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class InitThrowaleaseDataListener extends DefaultFuncNodeInitDataListener {
	
	private ShowUpableBillForm billForm;
	
	@Override
	public void initData(FuncletInitData data) {
			String nodecode=billForm.getBillCardPanel().getTempletData().getHeadVO().getNodecode();
			billForm.getBillCardPanel().getBillTable().removeSortListener();
			if(nodecode.equals("ZLH760")||nodecode.equals("ZLH740")){
				billForm.getBillCardPanel().setTatolRowShow(false);
			}
			super.initData(data);
	}
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
}
