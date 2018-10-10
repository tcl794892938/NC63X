package nc.ui.zl.cwf_recbill.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.zl.cwf_recbill.AggRecbillVO;

public class UNApproveAction extends nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction{

	

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj.length>500){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "选择数量最大为500条！");
			return;
		}
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<AggRecbillVO> agglist=new ArrayList<AggRecbillVO>();
		for(int i=0;i<obj.length;i++){
			AggRecbillVO aggvo=(AggRecbillVO) obj[i];
			agglist.add(aggvo);
		}
		AggRecbillVO [] aggvos=agglist.toArray(new AggRecbillVO[0]);
		for(AggRecbillVO aggvo:aggvos){
			String sql="select count(*) from zl_gather_b where nvl(dr,0)=0 and vsrcid='"+aggvo.getParentVO().getPk_recbill()+"'";
			int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(a>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "所选数据中存在有收款记录的单据，不可弃审！");
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
		List<AggRecbillVO> agglist=new ArrayList<AggRecbillVO>();
		for(int i=0;i<obj.length;i++){
			AggRecbillVO aggvo=(AggRecbillVO) obj[i];
			agglist.add(aggvo);
		}
		AggRecbillVO [] aggvos=agglist.toArray(new AggRecbillVO[0]);
		for(AggRecbillVO aggvo:aggvos){
			if(aggvo.getParentVO().getVbillstatus()!=1){
				return false;
			}
		}
		
		return true;
	}
				
}
