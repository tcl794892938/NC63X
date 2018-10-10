package nc.ui.zl.hql_contracttype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.ebpur.contractclass.config.contractclass;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.hql_contracttype.ContracttypeVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {
	ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBil1() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		ContracttypeVO vo = (ContracttypeVO) bill.getValue();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql2="select * from zl_contracttype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		ContracttypeVO vo2= (ContracttypeVO) iQ.executeQuery(sql2, new BeanProcessor(ContracttypeVO.class));
		String sql3="select count(*) from zl_contracttype where nvl(dr,0)=0 and pk_parentid='"+vo2.getPk_contracttype()+"'";
		int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
		if(a>0){
			MessageDialog.showHintDlg(bill, "提示", "存在下级，不可修改！请检查");
			return;
		}
		
		String sql10 = "select count(*) from zl_contract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count1=(Integer) iQ.executeQuery(sql10, new ColumnProcessor());
		if(count1>0){
			showDialog();
			return;
		}
		String sql20 = "select count(*) from zl_carcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count2=(Integer) iQ.executeQuery(sql20, new ColumnProcessor());
		if(count2>0){
			showDialog();
			return;
		}
		String sql30 = "select count(*) from zl_parkcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count3=(Integer) iQ.executeQuery(sql30, new ColumnProcessor());
		if(count3>0){
			showDialog();
			return;
		}
		super.doAction(e);
	}
	public void showDialog(){
		MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已被引用，不可修改！");
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
