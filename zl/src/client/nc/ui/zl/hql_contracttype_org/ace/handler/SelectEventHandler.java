package nc.ui.zl.hql_contracttype_org.ace.handler;

import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.vo.zl.hql_contracttype.ContracttypeVO;

public class SelectEventHandler {
	
	public void SelectEvent(AppEvent e,ShowUpableBillForm bill){
		HierachicalDataAppModel mol=(HierachicalDataAppModel)e.getSource();
		ContracttypeVO vo=(ContracttypeVO)mol.getSelectedData();
		if (vo == null) {
			bill.getBillCardPanel().getBillData().clearViewData();
			return;
		}
		bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
	}
}
