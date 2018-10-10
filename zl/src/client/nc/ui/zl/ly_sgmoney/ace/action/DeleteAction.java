package nc.ui.zl.ly_sgmoney.ace.action;

import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;

public class DeleteAction extends DeleteScriptAction {

	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null||obj.length>1){
			return false;
		}
		return super.isActionEnable();
	}
	
}
