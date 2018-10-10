package nc.ui.zl.ld_formattype_org.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.ld_formattype.FormattypeVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction{
	
	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		FormattypeVO fvo = (FormattypeVO)getModel().getSelectedData();
		
		String pk_org = getModel().getContext().getPk_org();
		if(!pk_org.equals("0001A910000000002NJX")){
			 if(fvo.getCode().length() == 2 || fvo.getVdef1().equals("0")){
				 MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "��ǰҵ̬Ϊ���ż��������޸ģ�");
				 return;
			 }
		}
		
		//��ȡ�ñ����¼�����
		String sql_2 = "select  code from zl_formattype where upname='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object soncode= iQ.executeQuery(sql_2, new ColumnProcessor());
		//�ϼ��޸��ж�
		if(soncode!=null){
			MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�����¼��������޸ı��룡����");
			return;
		}
		String sql_y = "select count(*) from zl_familyfile where pk_formattypeid = '"+fvo.getPk_formattype()+"'";
		int count = (Integer) iQ.executeQuery(sql_y, new ColumnProcessor());
		if(count > 0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "��ҵ̬�����ѱ����ã������޸ģ�");
			return;
		}
		super.doAction(e);
	}




}
