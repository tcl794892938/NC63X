package nc.ui.zl.base_project.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.base_project.AggProjectVO;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_customertype.CustomertypeVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		Object obj=getModel().getSelectedData();
		AggProjectVO vo=(AggProjectVO)obj;
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	    
		String sql = "select count(*) from zl_buildingfile where nvl(dr,0)=0 and " +
				     "pk_projectid='"+vo.getParentVO().getPk_project()+"'";
		String sql2= "select count(*) from zl_business_b where nvl(dr,0)=0 and procode='" +
				     vo.getParentVO().getPk_project()+"'";
		String sql3= "select count(*) from zl_business_b where nvl(dr,0)=0 and procode='" +
		             vo.getParentVO().getPk_project()+"'";
		int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
	    int b=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
	    int c=(Integer) iQ.executeQuery(sql3, new ColumnProcessor());
	    if(a>0 || b > 0 || c > 0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "项目已被引用，不可修改！");
			return;
		}
		super.doAction(e);
	}
}
