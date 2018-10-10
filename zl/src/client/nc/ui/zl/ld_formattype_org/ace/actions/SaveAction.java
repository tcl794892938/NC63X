package nc.ui.zl.ld_formattype_org.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;

import nc.vo.zl.ld_formattype.FormattypeVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.SaveAction{
	
	private ShowUpableBillForm bill;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		bill.getBillCardPanel().stopEditing();
		bill.getBillCardPanel().dataNotNullValidate();
		//��ȡǰ̨����
		FormattypeVO fvo = (FormattypeVO)bill.getValue();
		//���볤��
		int subcodelen = fvo.getCode().length();
		
		//��֤���ݿ��Ƿ���ڸı���
		String sql_1 = "select  count(*) from zl_formattype where code='"+fvo.getCode()+"' and nvl(dr,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count= (Integer)iQ.executeQuery(sql_1, new ColumnProcessor());
		
		//��ȡ�༶������ϼ�����
		UIRefPane scUIR=(UIRefPane) bill.getBillCardPanel().getHeadItem("upname").getComponent();
		Object fcode=scUIR.getRefCode();
		
		if(fcode!=null){//fvo.getCode().equals(fcode.toString().substring(0, fcode.toString().length()))
			if(fvo.getCode().length()!=fcode.toString().length()+2 || !fvo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(bill, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ�");
				return;
			}
		}
		
		//һ�������ж�
		if(fcode==null&&subcodelen!=2){
			MessageDialog.showHintDlg(bill, "��ʾ", "���볤��ӦΪ2��");
			return ;
			
		}
		
		
		
		//���ӱ���
		if(getModel().getUiState()==UIState.ADD){
			//Ψһ���ж�
			if(count>0){
				MessageDialog.showHintDlg(bill, "��ʾ", "���벻����Ψһ�ԣ�");
				return ;
			}
			
		}
		if(getModel().getUiState()==UIState.EDIT){
			
			//��ȡ�ñ����¼�����
			String sql_2 = "select  code from zl_formattype where upname='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
			Object soncode= iQ.executeQuery(sql_2, new ColumnProcessor());
			//ͨ��������ȡԭ����
			String sql_3 = "select  code from zl_formattype where pk_formattype='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
			Object code= iQ.executeQuery(sql_3, new ColumnProcessor());
			//�ϼ��޸��ж�
			if(soncode!=null){
				MessageDialog.showHintDlg(bill, "��ʾ", "�����¼��������޸ı��룡����");
				return;
			}else{
				if(count>0&&!code.toString().equals(fvo.getCode())){
					MessageDialog.showHintDlg(bill, "��ʾ", "���벻����Ψһ��");
					return;
				}
			}
		}
		
		
		
		
		super.doAction(e);
	}
	

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

}
