package nc.ui.zl.tcl_costtype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.tcl_costtype.CosttypeVO;

public class editAction extends nc.ui.pubapp.uif2app.actions.EditAction{
	
	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		
		CosttypeVO ctvo = (CosttypeVO)getModel().getSelectedData();
		String sql_y = "select count(*) from zl_costproject where nvl(dr,0)=0 and pk_costtype = '"+
	               ctvo.getPk_costtype()+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	    int yn = (Integer) iQ.executeQuery(sql_y, new ColumnProcessor());
	    if(yn > 0){
		    MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "当前类型被引用，不可修改！");
		    return ;
	    }
		//获取该编码下级编码
		String sql_2 = "select  code from zl_costtype where pk_vid='"+ctvo.getPk_costtype()+"' and nvl(dr,0)=0";
		
		Object soncode= iQ.executeQuery(sql_2, new ColumnProcessor());
		//上级修改判断
		if(soncode!=null){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "存在下级，不可修改编码！请检查");
			return;
		}
		
		super.doAction(e);
	}




}
