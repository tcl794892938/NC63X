package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_mdcontractMaintain;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

public class CancelAction extends nc.ui.pubapp.uif2app.actions.CancelAction {

	private ShowUpableBillForm billForm;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		//billForm.getBillCardPanel().setBillData(billForm.getBillCardPanel().getBillData());
		Object obj=billForm.getModel().getSelectedData();
		AggMdcontractVO aggvo=(AggMdcontractVO)obj;
		if(aggvo==null){
			super.doAction(e);
			return;
		}
		String pk=aggvo.getParentVO().getVdef1();
		
		super.doAction(e);
		if(!getStrObj(pk).equals("")){
			ILd_mdcontractMaintain itm=NCLocator.getInstance().lookup(ILd_mdcontractMaintain.class);
			AggMdcontractVO newaggvo=itm.queryHTbyPK(getStrObj(pk));
			AggMdcontractVO[] aggvos = new AggMdcontractVO[1];
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
