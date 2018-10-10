package nc.ui.zl.tcl_costtype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class SaveAction extends nc.ui.pubapp.uif2app.actions.SaveAction{
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		ShowUpableBillForm billform=(ShowUpableBillForm)getEditor();
		billform.getBillCardPanel().stopEditing();
		billform.getBillCardPanel().dataNotNullValidate();
		CosttypeVO vo=(CosttypeVO) billform.getValue();
		
		//
		UIRefPane scUIR=(UIRefPane) billform.getBillCardPanel().getHeadItem("pk_vid").getComponent();
		Object fcode=scUIR.getRefCode();
		if(fcode==null && vo.getCode().length()!=2){
			MessageDialog.showHintDlg(billform, "��ʾ", "һ�����볤�ȱ���Ϊ2λ��");
			return;
		}
		if(fcode!=null){
			
			if(vo.getCode().length()!=(fcode.toString().length()+2) || !vo.getCode().substring(0, fcode.toString().length()).equals(fcode)){
				MessageDialog.showHintDlg(billform, "��ʾ", "�������ӦΪ�ϼ��������λ�ַ�����ϣ�");
				return;
			}
		}
		String sql="select count(*) from zl_costtype where nvl(dr,0)=0 and code='"+vo.getCode()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count =(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(getModel().getUiState()==UIState.ADD){
			if(count>0){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
		}
		if(getModel().getUiState()==UIState.EDIT){
			String sql2="select * from zl_costtype where nvl(dr,0)=0 and pk_costtype='"+vo.getPk_costtype()+"'";
			CosttypeVO vo2= (CosttypeVO) iQ.executeQuery(sql2, new BeanProcessor(CosttypeVO.class));
			
			String sql3="select count(*) from zl_costtype where nvl(dr,0)=0 and pk_vid='"+vo2.getPk_costtype()+"'";
			int a=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
			if(a>0&&!vo.getCode().equals(vo2.getCode())&&!vo.getName().equals(vo2.getName())){
				MessageDialog.showHintDlg(billform, "��ʾ", "�����¼��������޸ı��룬���飡");
				return;
			}
			
			if(vo.getCode().equals(vo2.getCode())&& count>1){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
			if(!vo.getCode().equals(vo2.getCode()) && count >0){
				MessageDialog.showHintDlg(billform, "��ʾ", "���벻����Ψһ��");
				return;
			}
		}
		
		super.doAction(e);
	}
	
}
