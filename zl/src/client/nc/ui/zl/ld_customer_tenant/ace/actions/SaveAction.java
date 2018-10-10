package nc.ui.zl.ld_customer_tenant.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.DifferentVOSaveAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.lm_customer.AggCustomerVO;

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
		AggCustomerVO cvo = (AggCustomerVO)billform.getValue();
		//判断编码唯一性
		String sql_1 = "select  count(*) from zl_customer where customercode='"+cvo.getParentVO().getCustomercode()+"' and nvl(dr,0)=0";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		int count= (Integer)iQ.executeQuery(sql_1, new ColumnProcessor());
		
		
		if(getModel().getUiState()==UIState.EDIT){
			String sql_2 = "select customercode from zl_customer where pk_customer='"+cvo.getParentVO().getPk_customer()+"' and nvl(dr,0)=0";
			Object code= iQ.executeQuery(sql_2, new ColumnProcessor());
			if((code.toString().equals(cvo.getParentVO().getCustomercode())&&count==1)){
				super.doAction(e);
				return;
			}
			
		}
		if(count>0){
			MessageDialog.showHintDlg(billform, "提示", "编码不符合唯一性");
			return;
		}
		super.doAction(e);
	}

	private UFDouble getUfd(UFDouble ud){
		return ud==null?new UFDouble(0):ud;
	}

}
