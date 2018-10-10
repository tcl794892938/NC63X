package nc.ui.zl.hql_contracttype_org.ace.actions;

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
		// TODO �Զ����ɵķ������
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		ContracttypeVO vo=(ContracttypeVO) obj;
		if(vo.getVdef1().equals("0")){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�ò˵�Ϊ�������ӣ�����ɾ��������");
			return false ;
		}
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select count(*) from zl_contracttype where nvl(dr,0)=0 and pk_parentid='"+vo.getPk_contracttype()+"'";
		
		int count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�����¼�������ɾ����");
			return false;
		}
		
		String sql1 = "select count(*) from zl_mdcontract z where nvl(dr,0)=0 and z.pk_contracttype='"+vo.getPk_contracttype()+"'";
		int count1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
		if(count1>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�ѱ����ã�����ɾ����");
			return false;
		}
		
		return super.beforeStartDoAction(actionEvent);
	}
	
	/*@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)
			throws Exception {
		// TODO �Զ����ɵķ������
		ContracttypeVO vo = (ContracttypeVO) bill.getModel().getSelectedData();
		if(vo == null){
			return false;
		}
		String code = vo.getCode();
		//��ѯ���ݿ�
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql="select count(*) from zl_contracttype where nvl(dr,0)=0"+
		            " and pk_parentid = '"+code+"'";
		int count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count > 0){
			MessageDialog.showHintDlg(bill, "��ʾ", "��ǰ���ʹ����¼�������ɾ����");
			return false;
		}
		return super.beforeStartDoAction(actionEvent);
	}*/
	
	
	
}
