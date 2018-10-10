package nc.ui.zl.lyw_confirmation.ace.actions;

import java.util.ArrayList;
import java.util.List;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

public class CommitAction extends CommitScriptAction {
	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null){
			return false;
		}
		List<AggConfirmationVO> agglist=new ArrayList<AggConfirmationVO>();
		for(int i=0;i<obj.length;i++){
			AggConfirmationVO aggvo=(AggConfirmationVO) obj[i];
			agglist.add(aggvo);
		}
		AggConfirmationVO [] aggvos=agglist.toArray(new AggConfirmationVO[0]);
		for(AggConfirmationVO aggvo:aggvos){
			if(aggvo.getParentVO().getVbillstatus()!=-1){
				return false;
			}
		}
		return true;
	}
}
