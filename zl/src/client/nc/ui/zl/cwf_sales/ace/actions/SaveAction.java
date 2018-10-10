package nc.ui.zl.cwf_sales.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.cwf_sales.AggSalesVO;

public class SaveAction extends DifferentVOSaveAction{
	public BillForm billform;


	public BillForm getBillform() {
	return billform;
}

public void setBillform(BillForm billform) {
	this.billform = billform;
}

@Override
public void doAction(ActionEvent e) throws Exception {
	billform.getBillCardPanel().stopEditing();
	billform.getBillCardPanel().dataNotNullValidate();
	
	AggSalesVO aggvo = (AggSalesVO) billform.getValue();
	IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
	String code = aggvo.getParentVO().getCode();
	String sql = "select count(*) from zl_sales where nvl(dr,0)=0 and code = '"+code+"'";
	int count = (Integer) iQ.executeQuery(sql, new ColumnProcessor());
	if(getModel().getUiState() == UIState.EDIT){
		String sql2 = "select code from zl_sales where nvl(dr,0) = 0 and pk_sales = '"+
	                  aggvo.getParentVO().getPk_sales()+"'";
		Object codeObj = iQ.executeQuery(sql2, new ColumnProcessor());
		if(codeObj.toString().equals(code)){
			super.doAction(e);
			return;
		}else{
			MessageDialog.showHintDlg(billform, "提示", "编码不可修改！");
			return;
		}
	}
	if(count > 0){
		MessageDialog.showHintDlg(billform, "提示", "该业务员权限控制下编码已存在！");
		return;
	}
	
		super.doAction(e);
}

	private UFDouble getUfd(UFDouble ud){
		return ud==null?new UFDouble(0):ud;
	}

}
