package nc.ui.zl.cwf_recbill.ace.actions;

import nc.ui.pubapp.uif2app.model.BillManageModel;

public class LinkQueryAction extends nc.ui.pubapp.uif2app.actions.LinkQueryAction{

	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null||obj.length>1){
			return false;
		}
		return super.isActionEnable();
	}
	
	
}
