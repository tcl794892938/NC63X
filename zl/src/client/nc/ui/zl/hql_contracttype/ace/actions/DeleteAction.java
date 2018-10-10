package nc.ui.zl.hql_contracttype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)
			throws Exception {
		// TODO 自动生成的方法存根
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		ContracttypeVO vo=(ContracttypeVO) obj;
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select count(*) from zl_contracttype where nvl(dr,0)=0 and pk_parentid='"+vo.getPk_contracttype()+"'";
		
		int count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "存在下级，不可删除！");
			return false;
		}
		
		String sql1 = "select count(*) from zl_contract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
		if(count1>0){
			showDialog();
			return false;
		}
		String sql2 = "select count(*) from zl_carcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count2=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
		if(count2>0){
			showDialog();
			return false;
		}
		String sql3 = "select count(*) from zl_parkcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count3=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
		if(count3>0){
			showDialog();
			return false;
		}
		return super.beforeStartDoAction(actionEvent);
	}
	
	public void showDialog(){
		MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已被引用，不可删除！");
	}
	

	
	/*@Override
	protected boolean isActionEnable() {
		ContracttypeVO cvo = (ContracttypeVO)getModel().getSelectedData();
		if(cvo==null){
			return false;
		}
		if(cvo.getCode().length()==2){
			return false;
		}
		return true;
	}*/
	
	
	
}
