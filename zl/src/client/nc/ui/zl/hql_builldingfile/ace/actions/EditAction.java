package nc.ui.zl.hql_builldingfile.ace.actions;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {
	@Override
	protected boolean isActionEnable() {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		AggBuildingfileVO vo=(AggBuildingfileVO)obj;
		UFBoolean ub=vo.getParentVO().getIsbuild();
		if(ub.booleanValue()==false){
			return true;
		}
		
		return false;
	}
	
}
