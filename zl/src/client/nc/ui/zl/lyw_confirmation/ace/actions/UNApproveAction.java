package nc.ui.zl.lyw_confirmation.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

public class UNApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction{
	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj.length>500){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "选择数量最大为500条！");
			return;
		}
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<AggConfirmationVO> agglist=new ArrayList<AggConfirmationVO>();
		for(int i=0;i<obj.length;i++){
			AggConfirmationVO aggvo=(AggConfirmationVO) obj[i];
			agglist.add(aggvo);
		}
		AggConfirmationVO [] aggvos=agglist.toArray(new AggConfirmationVO[0]);
		for(AggConfirmationVO aggvo:aggvos){
			String sql="select count(*) from zl_billconfirmedb where nvl(dr,0)=0 and vsrcid='"+aggvo.getParentVO().getPk_confirmation()+"'";
			int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(a>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "所选数据中存在下游单据，不可弃审！");
				return;
			}
		}
		super.doAction(e);
	}

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
			if(aggvo.getParentVO().getVbillstatus()!=1){
				return false;
			}
		}
		return true;
	}
}
