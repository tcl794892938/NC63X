package nc.ui.zl.hql_customertype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction{

	@Override
	public void doAction(ActionEvent e) throws Exception {
		CustomertypeVO cvo = (CustomertypeVO)getModel().getSelectedData();
		
		
		if(cvo!=null){
			
			
			String sql = "select  count(vdef1) from zl_customertype where pk_parentid='"+cvo.getPk_customertype()+"' and nvl(dr,0)=0  and vdef1='1'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Integer count = (Integer)iQ.executeQuery(sql, new ColumnProcessor());
			if(count!=0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "下级存在组织级项目，不可增加！请检查");
				return;
			}
		}
		super.doAction(e);
	}
	
}
