package nc.ui.zl.hql_familyfile.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
		Object obj=getModel().getSelectedData();
		AggFamilyfileVO vo=(AggFamilyfileVO)obj;
		String pk=vo.getParentVO().getPk_familyfile();
		String sql="select count(1) from zl_familyfile f where pk_familyfile='"+pk+"' "
			+ "and exists(select 1 from zl_housesource e where e.pk_familyfile=f.pk_familyfile and nvl(e.dr,0)=0)";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "��ʾ", "�����ѱ����ã�����ɾ����");
			return ;
		}
		
		super.doAction(e);
	}
	
	@Override
	protected boolean isActionEnable() {
		
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		return true;
	}
	
}
