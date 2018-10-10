package nc.ui.zl.tcl_costtype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.BusinessException;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction{

	
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {

		Object obj=getModel().getSelectedData();
		if(obj==null){
			return ;
		}
		CosttypeVO vo=(CosttypeVO) obj;
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql_y = "select count(*) from zl_costproject where nvl(dr,0)=0 and pk_costtype = '"+
	               vo.getPk_costtype()+"'";
	    int yn = (Integer) iQ.executeQuery(sql_y, new ColumnProcessor());
	    if(yn > 0){
		    MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "当前类型被引用，不可删除！");
		    return ;
	    }
		String sql="select count(*) from zl_costtype where nvl(dr,0)=0 and pk_vid='"+vo.getPk_costtype()+"'";
		
			int count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(count>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "存在下级，不可删除！");
				return;
			}
				super.doAction(arg0);
		
	}

	
}
