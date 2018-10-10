package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ITcl_contractMaintain;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.tcl_contract.AggContractVO;

public class CancelAction extends nc.ui.pubapp.uif2app.actions.CancelAction {

	private ShowUpableBillForm billForm;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		//billForm.getBillCardPanel().setBillData(billForm.getBillCardPanel().getBillData());
		Object obj=billForm.getModel().getSelectedData();
		AggContractVO aggvo=(AggContractVO)obj;
		if(aggvo==null){
			super.doAction(e);
			return;
		}
		Object pk=aggvo.getParentVO().getVdef1();
		
		super.doAction(e);
		if(!getStrObj(pk).equals("")){
			ITcl_contractMaintain itm=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
			AggContractVO newaggvo=itm.queryHTbyPK(getStrObj(pk));
			AggContractVO[] aggvos = new AggContractVO[1];
			aggvos[0]=newaggvo;
			//billForm.getBillCardPanel().setBillValueVO(newaggvo);
			//billForm.getBillCardPanel().
			billForm.getModel().directlyUpdate(aggvos);
		}

	}
	public String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
}
