package nc.ui.zl.tcl_contract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;

public class SavemdAction extends SaveScriptAction {

	private static final long serialVersionUID = 8196125762659199432L;
	
	private ShowUpableBillForm billForm;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		BillCardPanel panel=billForm.getBillCardPanel();
		panel.stopEditing();
		panel.dataNotNullValidate();
		//校验合同号重复
		Object pkobj=panel.getHeadItem("vsrcid").getValueObject();
		Object code=panel.getHeadItem("htcode").getValueObject();
		String sql="select count(1) from zl_contract where nvl(dr,0)=0 and vbilltypecode='H420' and " +
				" version=-1 and htcode='"+code+"' and pk_contract<>'"+pkobj+"'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			MessageDialog.showHintDlg(panel, "提示", "合同号重复！");
			return ;
		}
		
		CalculateRentUtils.recalRent(panel);//租金重算
		super.doAction(e);
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	

}
