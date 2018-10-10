package nc.ui.zl.hql_customertype_org.ace.handler;

import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class SelectEventHandler {
	
	public void SelectEvent(AppEvent e,ShowUpableBillForm bill){
		HierachicalDataAppModel mol=(HierachicalDataAppModel)e.getSource();
		CustomertypeVO vo=(CustomertypeVO)mol.getSelectedData();
		if (vo == null) {
			bill.getBillCardPanel().getBillData().clearViewData();
			return;
		}
		bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
	}
}
