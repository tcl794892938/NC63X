package nc.ui.zl.tcl_costtype.ace.handler;

import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class SelectEventHandler {

	public void SelectEvent(AppEvent e, ShowUpableBillForm bill) {

		HierachicalDataAppModel mol = (HierachicalDataAppModel) e.getSource();
		CosttypeVO vo = (CosttypeVO) mol.getSelectedData();
		if (vo == null) {
			bill.getBillCardPanel().getBillData().clearViewData();
			return;
		}
		bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
	}

}
