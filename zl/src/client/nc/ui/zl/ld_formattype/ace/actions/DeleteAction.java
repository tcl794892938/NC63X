package nc.ui.zl.ld_formattype.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.ld_formattype.FormattypeVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {

	/*@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		return false;
	}*/
	
	private ShowUpableBillForm bill;
	
	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)throws Exception {
		Object obj=getModel().getSelectedData();
		if(obj==null){
			return false;
		}
		
		String pk_org = getModel().getContext().getPk_org();
		
		FormattypeVO fvo = (FormattypeVO)obj;
		String sql_1 = "select  code from zl_formattype where upname='"+fvo.getPk_formattype()+"' and nvl(dr,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object Objcode= iQ.executeQuery(sql_1, new ColumnProcessor());
		if(Objcode!=null){
			//MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), "提示", "存在下级，不可删除！");
			MessageDialog.showHintDlg(bill, "提示", "存在下级，不可删除！");
			return false;
		}
		String sql_y = "select count(*) from zl_familyfile where pk_formattypeid = '"+fvo.getPk_formattype()+"'";
		int count = (Integer) iQ.executeQuery(sql_y, new ColumnProcessor());
		if(count > 0){
			MessageDialog.showHintDlg(bill, "提示", "该业态类型已被引用，不可删除！");
			return false;
		}
		return super.beforeStartDoAction(actionEvent);
	}
	
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}
	
}
