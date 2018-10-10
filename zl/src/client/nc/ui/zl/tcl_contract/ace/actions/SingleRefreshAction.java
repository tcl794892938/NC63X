package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ITcl_contractMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.zl.tcl_contract.AggContractVO;

public class SingleRefreshAction extends NCAction {

	private static final long serialVersionUID = -7547505128209608549L;
	
	private BillManageModel model;
	private ShowUpableBillForm billform;
	
	public SingleRefreshAction(){
		this.setBtnName("Ë¢ÐÂ");
		this.setCode("cardrefresh");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=model.getSelectedData();
		AggContractVO aggvo=(AggContractVO)obj;
		String pk=aggvo.getParentVO().getPk_contract();
		
		ITcl_contractMaintain itm=NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
		AggContractVO newagg=itm.queryHTbyPK(pk);
		
		getModel().directlyUpdate(newagg);

	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	@Override
	protected boolean isActionEnable() {
		Object obj=model.getSelectedData();
		if(obj==null){
			return false;
		}
		return true;
	}
	

}
