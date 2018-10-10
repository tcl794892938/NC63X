package nc.ui.zl.ly_zylist.ace.action;

import java.awt.event.ActionEvent;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.zl.cwf_recbill.RecbillVO;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.zl.ly_zylist.ZylistVO;

public class UnApproveAction extends UNApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		AggZylistVO aggvo=(AggZylistVO)obj;
		ZylistVO zvo=aggvo.getParentVO();
		Object pk_zylist=zvo.getPk_zylist();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql1="select pk_hflist from zl_hflist where nvl(dr,0)=0 and vsrcid='"+getStgObj(pk_zylist)+"'";
		Object hflist=iQ.executeQuery(sql1, new ColumnProcessor());
		if(!getStgObj(hflist).equals("")){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"已生成回访单无法取消审批！");
			return;
		}
		
		super.doAction(e);
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
