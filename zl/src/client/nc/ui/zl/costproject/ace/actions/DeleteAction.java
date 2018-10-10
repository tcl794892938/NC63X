package nc.ui.zl.costproject.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.zl.cwf_costproject.CostprojectVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction{

	
	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)throws Exception {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		CostprojectVO vo=(CostprojectVO) obj;
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql_y = "select count(*) from zl_feescale where chargeitem = '"+vo.getPk_costproject()+"'";
		int count_y = (Integer) iQ.executeQuery(sql_y, new ColumnProcessor());
		if(count_y > 0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "当前收费项目已被引用，不可删除！请检查");
			return false;
		}
		String sql="select count(*) from zl_costproject where nvl(dr,0)=0 and pk_vid='"+vo.getPk_costproject()+"'";
		
			int count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
			if(count>0){
				MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "存在下级，不可删除！");
				return false;
			}
		
		return super.beforeStartDoAction(actionEvent);
	}
	

	
}
