package nc.ui.zl.hql_customertype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {
	ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		CustomertypeVO vo = (CustomertypeVO) bill.getValue();
		String sql2="select * from zl_customertype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		CustomertypeVO vo2= (CustomertypeVO) iQ.executeQuery(sql2, new BeanProcessor(CustomertypeVO.class));
		String sql3="select count(*) from zl_customertype where nvl(dr,0)=0 and pk_parentid='"+vo2.getPk_customertype()+"'";
		int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
		if(a>0){
			MessageDialog.showHintDlg(bill, "提示", "存在下级，不可修改编码！请检查");
			return;
		}
		//判断是否被引用
		String sql_1 = "select count(*) from zl_business where nvl(dr,0)=0 and  customertype='"+vo.getPk_customertype()+"'";
		String sql_2 = "select count(*) from zl_pocustomers where nvl(dr,0)=0 and  customertype='"+vo.getPk_customertype()+"'";
		String sql_3 = "select count(*) from zl_customer where nvl(dr,0)=0 and  customertype='"+vo.getPk_customertype()+"'";
		int count_1=(Integer) iQ.executeQuery(sql_1, new ColumnProcessor());
		int count_2=(Integer) iQ.executeQuery(sql_2, new ColumnProcessor());
		int count_3=(Integer) iQ.executeQuery(sql_3, new ColumnProcessor());
		if(count_1>0||count_2>0||count_3>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "该类型已被引用，不可修改！");
			return ;
		}
		super.doAction(e);
	}
	
	/*@Override
	protected boolean isActionEnable() {
		CustomertypeVO cvo = (CustomertypeVO)getModel().getSelectedData();
		if(cvo==null||cvo.getCode().length()==2||cvo.getCode().equals("0201")||cvo.getCode().equals("0202")||cvo.getCode().equals("0203")){
			return false;
		}
		return true;
	}*/
	
}
