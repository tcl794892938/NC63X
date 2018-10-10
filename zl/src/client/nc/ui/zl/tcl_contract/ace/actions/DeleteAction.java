package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.zl.tcl_contract.ContractVO;

public class DeleteAction extends DeleteScriptAction {

	private static final long serialVersionUID = -6880008811554779252L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		//校验是否有下游应收单
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return ;
		}
		AggContractVO aggvo=(AggContractVO)obj;
		ContractVO vo=aggvo.getParentVO();
		String sql="select count(1) from zl_recbill where nvl(dr,0)=0 and vsrcid='"+vo.getPk_contract()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已有下游应收单，不允许删除!");
			return ;
		}
		super.doAction(e);
	}
	
	

}
