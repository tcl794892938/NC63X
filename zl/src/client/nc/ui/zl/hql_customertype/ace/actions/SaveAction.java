package nc.ui.zl.hql_customertype.ace.actions;

import java.awt.event.ActionEvent;

import com.ibm.db2.jcc.a.d;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
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
		// TODO �Զ����ɵķ������
		//ShowUpableBillForm billform=(ShowUpableBillForm)getEditor();
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		CustomertypeVO vo=(CustomertypeVO) bill.getValue();
		
		UIRefPane scUIR=(UIRefPane) bill.getBillCardPanel().getHeadItem("pk_parentid").getComponent();
		Object fcode=scUIR.getRefCode();
		if(fcode==null && vo.getCode().length()!=2){
			MessageDialog.showHintDlg(bill, "��ʾ", "һ�����볤�ȱ���Ϊ2λ��");
			return;
		}
		if(fcode!=null){
			if(vo.getCode().length()!=(fcode.toString().length()+2) || !vo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(bill, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ�");
				return;
			}
		}
		String sql="select count(*) from zl_customertype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count =(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(getModel().getUiState()==UIState.ADD){
			if(count>0){
				MessageDialog.showHintDlg(bill, "��ʾ", "���벻����Ψһ��");
				return;
			}
			
			BillCardPanel panel = bill.getBillCardPanel();
			//��ȡ����
			panel.setHeadItem("pk_org","0001A910000000000LRO");
			//��������
			panel.setHeadItem("vdef1", "0");
		}
		if(getModel().getUiState()==UIState.EDIT){
			
			String sql2="select * from zl_customertype where nvl(dr,0)=0 and pk_customertype='"+vo.getPk_customertype()+"'";
			CustomertypeVO vo2= (CustomertypeVO) iQ.executeQuery(sql2, new BeanProcessor(CustomertypeVO.class));
			String sql3="select count(*) from zl_customertype where nvl(dr,0)=0 and pk_parentid='"+vo.getPk_customertype()+"'";
			int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
			if(a>0&&!vo.getCode().equals(vo2.getCode())){
				MessageDialog.showHintDlg(bill, "��ʾ", "�����¼��������޸ı��룡����");
				return;
			}
			if(vo.getCode().equals(vo2.getCode())&& count>1){
				MessageDialog.showHintDlg(bill, "��ʾ", "���벻����Ψһ��");
				return;
			}
			if(!vo.getCode().equals(vo2.getCode()) && count >0){
				MessageDialog.showHintDlg(bill, "��ʾ", "���벻����Ψһ��");
				return;
			}
		}
		
		super.doAction(e);
	}
}
