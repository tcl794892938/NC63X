package nc.ui.zl.hql_jt_acceptance.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {

	private ShowUpableBillForm billform;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object pk=billform.getBillCardPanel().getHeadItem("pk_acceptance").getValueObject();
		String sql="select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and pk_parent='"+getStgObj(pk)+"'";
		Integer count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		if(count>0){
			billform.getBillCardPanel().getHeadItem("code").setEdit(false);
		}
		AggJt_acceptanceVO aggvo = (AggJt_acceptanceVO) getModel().getSelectedData();
		/*String sql_count = "select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and " +
				           "pk_parent = '" + aggvo.getParentVO().getPk_acceptance() + "'";
		int count = (Integer) iQ.executeQuery(sql_count, new ColumnProcessor());
		if(count > 0){
			MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", 
					                  "存在下级，不可修改编码！请检查");
			return;
		}*/
		String sql_jc = "select count(*) from zl_entryacceptance_jcys where nvl(dr,0)=0" +
				         " and pk_jt_acceptance = '"+aggvo.getParentVO().getPk_acceptance()+"'";
		int jc_count = (Integer) iQ.executeQuery(sql_jc, new ColumnProcessor());
		if(jc_count > 0){
			throw new Exception("已被引用，不可修改！");
		}
		String sql_tc = "select count(*) from zl_throwalease_tzys where nvl(dr,0)=0" +
		         " and pk_acceptance = '"+aggvo.getParentVO().getPk_acceptance()+"'";
        int tc_count = (Integer) iQ.executeQuery(sql_tc, new ColumnProcessor());
        if(tc_count > 0){
        	throw new Exception("已被引用，不可修改！");
        }
		super.doAction(e);
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
}
