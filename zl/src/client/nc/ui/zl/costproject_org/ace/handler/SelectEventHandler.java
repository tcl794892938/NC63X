package nc.ui.zl.costproject_org.ace.handler;

import nc.ui.pubapp.uif2app.model.HierachicalDataAppModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.AppEvent;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class SelectEventHandler{

	public void SelectEvent(AppEvent e,ShowUpableBillForm bill) {
		
		HierachicalDataAppModel mol=(HierachicalDataAppModel)e.getSource();
		CostprojectVO vo=(CostprojectVO)mol.getSelectedData();
		if (vo == null) {
			bill.getBillCardPanel().getBillData().clearViewData();
			return;
		}
		bill.getBillCardPanel().getBillData().setHeaderValueVO(vo);
	}

}