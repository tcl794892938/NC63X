package nc.ui.zl.hql_familyfile.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {
	private ShowUpableBillForm billform;
	
	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		AggFamilyfileVO aggvo = (AggFamilyfileVO) billform.getValue();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql2 = "select count(1) from zl_housesource where pk_familyfile = '"+
                aggvo.getParentVO().getPk_familyfile()+"' and nvl(dr,0)=0";
	int it2 = (Integer) iQ.executeQuery(sql2, new ColumnProcessor());
	if(it2 > 0){
		MessageDialog.showHintDlg(billform, "提示", "该户型档案已被引用，不能修改！");
		return ;
	}
		super.doAction(e);
	}
	
}
