package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

public class UnApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7167176480432217459L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		AggMdcontractVO aggvo = (AggMdcontractVO) getModel().getSelectedData();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		//BaseDAO dao=new BaseDAO();
		String sql = "select count(*) from zl_upcontract  where nvl(dr,0)=0 and vsrcid ='"+aggvo.getParentVO().getPk_mdcontract()+"'";
		int count1 = (Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count1>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已被多种经营合同修订参照，不可取消！");
				return;
		}
		
		super.doAction(e);
	}
}
