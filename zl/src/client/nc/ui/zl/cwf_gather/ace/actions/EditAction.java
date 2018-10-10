package nc.ui.zl.cwf_gather.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.zl.cwf_gather.AggGatherVO;


public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {
  
	public BillForm billform;
	
	public BillForm getBillform() {
		return billform;
	}

	public void setBillform(BillForm billform) {
		this.billform = billform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		super.doAction(e);
		AggGatherVO aggvo=(AggGatherVO) getModel().getSelectedData();
		if(!aggvo.getParentVO().getIsadd().booleanValue()){
			this.billform.getBillCardPanel().getBodyItem("pk_building").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("pk_house").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("pk_carno").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("pk_costproject").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("begindate").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("enddate").setEnabled(false);
			this.billform.getBillCardPanel().getBodyItem("nskmny").setEnabled(false);
			this.billform.getBillCardPanel().getHeadItem("pk_project").setEnabled(false);
			this.billform.getBillCardPanel().getHeadItem("pk_customer").setEnabled(false);
			this.billform.getBillCardPanel().getHeadItem("nskmny").setEnabled(true);
		}
		
	}
	
	
}
