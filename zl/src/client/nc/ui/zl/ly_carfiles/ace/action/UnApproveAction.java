package nc.ui.zl.ly_carfiles.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.zl.ly_bslist.BslistVO;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.zl.ly_carfiles.CarFilesVO;

public class UnApproveAction extends UNApproveScriptAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getModel().getSelectedData();
		AggCarFilesVO aggvo=(AggCarFilesVO)obj;
		CarFilesVO vo=aggvo.getParentVO();
		Object pk_carfiles=vo.getPk_carfiles();
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select pk_carcontract from zl_carcontract where nvl(dr,0)=0 and vsrcbid='"+getStgObj(pk_carfiles)+"'";
		Object carcontract=iQ.executeQuery(sql, new ColumnProcessor());
		
		if(getStgObj(carcontract).equals("")){
			super.doAction(e);
		}else{
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"存在下游数据无法取消审批！");
		}
		
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
