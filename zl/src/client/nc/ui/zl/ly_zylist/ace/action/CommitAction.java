package nc.ui.zl.ly_zylist.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistSrVO;

public class CommitAction extends CommitScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj = getModel().getSelectedData();
		AggZylistVO aggvo = (AggZylistVO) obj;
		ZylistSrVO[] zsvos = (ZylistSrVO[]) aggvo.getChildrenVO();
		
		if(zsvos.length<=0){
			MessageDialog.showErrorDlg(null, "错误", "表体数据为空不能提交！");
			return;
		}
		
		super.doAction(e);
	}

	
	
}
