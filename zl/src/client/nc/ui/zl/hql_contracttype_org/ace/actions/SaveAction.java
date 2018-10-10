package nc.ui.zl.hql_contracttype_org.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.ui.zl.abs.refmodel.ContracttypeRefModel;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.SaveAction {
	private ShowUpableBillForm bill;
		
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}
	
	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		//ShowUpableBillForm billform=(ShowUpableBillForm)getEditor();
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		ContracttypeVO vo=(ContracttypeVO) bill.getValue();
		UIRefPane scUIR=(UIRefPane) bill.getBillCardPanel().getHeadItem("pk_parentid").getComponent();
		Object fcode=scUIR.getRefCode();
		if(fcode==null && vo.getCode().length()!=2){
			MessageDialog.showHintDlg(bill, "提示", "一级编码长度必须为2位！");
			return;
		}
		if(fcode!=null){
			if(vo.getCode().length()!=(fcode.toString().length()+2) || !vo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(bill, "提示", "编码规则应为上级编码和两位字符的组合！");
				return;
			}
		}
		String sql="select count(*) from zl_contracttype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count =(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(getModel().getUiState()==UIState.ADD){
			if(count>0){
				MessageDialog.showHintDlg(bill, "提示", "编码不符合唯一性");
				return;
			}
			
			BillCardPanel panel = bill.getBillCardPanel();
			panel.setHeadItem("vdef1", '1');
		}
		if(getModel().getUiState()==UIState.EDIT){
			
			String sql2="select * from zl_contracttype where nvl(dr,0)=0 and pk_contracttype='"+vo.getPk_contracttype()+"'";
			ContracttypeVO vo2= (ContracttypeVO) iQ.executeQuery(sql2, new BeanProcessor(ContracttypeVO.class));
			ContracttypeVO defvo = (ContracttypeVO) bill.getModel().getSelectedData();
			String sql3="select count(*) from zl_contracttype where nvl(dr,0)=0 and pk_parentid='"+vo.getPk_contracttype()+"'";
			int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
			if(a>0&&!vo.getCode().equals(vo2.getCode())&&!vo.getName().equals(vo2.getName())){
				MessageDialog.showHintDlg(bill, "提示", "存在下级，不可修改编码！请检查");
				return;
			}
			if(vo.getCode().equals(vo2.getCode())&& count>1){
				MessageDialog.showHintDlg(bill, "提示", "编码不符合唯一性");
				return;
			}
			if(!vo.getCode().equals(vo2.getCode()) && count >0){
				MessageDialog.showHintDlg(bill, "提示", "编码不符合唯一性");
				return;
			}
		}
		
		super.doAction(e);
	}
	
}