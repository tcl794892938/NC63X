package nc.ui.zl.hql_contracttype.ace.actions;

import java.awt.event.ActionEvent;


import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.hql_contracttype.ContracttypeVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		ContracttypeVO cvo = (ContracttypeVO)getModel().getSelectedData();
		
		/*if(cvo == null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "һ������ΪϵͳԤ�ã�������������");
			return;
		}*/
		if(cvo!=null){
			
			
			String sql = "select  count(vdef1) from zl_contracttype where pk_parentid='"+cvo.getPk_contracttype()+"' and nvl(dr,0)=0  and vdef1='1'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Integer count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
			if(count!=0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�¼�������֯����Ŀ���������ӣ�����");
				return;
			}
		}
		super.doAction(e);
	}
}
