package nc.ui.zl.cwf_recbill.ace.actions;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.zl.cwf_recbill.AggRecbillVO;

public class CommitAction extends CommitScriptAction {



	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null){
			return false;
		}
		List<AggRecbillVO> agglist=new ArrayList<AggRecbillVO>();
		for(int i=0;i<obj.length;i++){
			AggRecbillVO aggvo=(AggRecbillVO) obj[i];
			agglist.add(aggvo);
		}
		AggRecbillVO [] aggvos=agglist.toArray(new AggRecbillVO[0]);
		for(AggRecbillVO aggvo:aggvos){
			if(aggvo.getParentVO().getVbillstatus()!=-1){
				return false;
			}
		}
		
		return true;
	}
	
}
