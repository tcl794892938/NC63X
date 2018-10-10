package nc.ui.zl.hql_contracttype_org.ace.actions;

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
		ContracttypeVO vo = (ContracttypeVO) getModel().getSelectedData();
		if(vo.getVdef1().equals("0")){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "该菜单为集团增加，不可修改！请检查");
			return;
		}
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql2="select * from zl_contracttype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		ContracttypeVO vo2= (ContracttypeVO) iQ.executeQuery(sql2, new BeanProcessor(ContracttypeVO.class));
		String sql3="select count(*) from zl_contracttype where nvl(dr,0)=0 and pk_parentid='"+vo2.getPk_contracttype()+"'";
		int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
		if(a>0){
			MessageDialog.showHintDlg(bill, "提示", "存在下级，不可修改编码！请检查");
			return;
		}
		
		String sql1 = "select count(*) from zl_mdcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
		if(count1>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "已被引用，不可修改！");
			return ;
		}
		super.doAction(e);
	}
	
}
