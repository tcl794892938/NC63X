package nc.ui.zl.ly_sgmoney.ace.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;

public class UnCommitAction extends UnCommitScriptAction
 {

	

	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null){
			return false;
		}
		List<AggSgMoneyVO> agglist=new ArrayList<AggSgMoneyVO>();
		for(int i=0;i<obj.length;i++){
			AggSgMoneyVO aggvo=(AggSgMoneyVO) obj[i];
			agglist.add(aggvo);
		}
		AggSgMoneyVO [] aggvos=agglist.toArray(new AggSgMoneyVO[0]);
		for(AggSgMoneyVO aggvo:aggvos){
			if(aggvo.getParentVO().getListstate()!=3){
				return false;
			}
		}
		
		return true;
	}
	
}
