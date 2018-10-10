package nc.ui.zl.ly_pocustomers.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class BodyDelLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction {

	private ShowUpableBillForm billform;
	private BillManageModel model;

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		Object obj=getBillform().getBillCardPanel().getBillTable().getSelectedRow();
		Integer row=(Integer)(obj==null?"":obj);
		Object vdef1 = getBillform().getBillCardPanel().getBillModel().getValueAt(row, "vdef1");
		String s = vdef1 == null ? "" : vdef1.toString();
		if (s.equals("noedit")) {
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示",
					"该条数据是生成数据不可删除！");
			return;
		}
		
		super.doAction(e);
		
		Object obj2=getBillform().getBillCardPanel().getBillTable("id_pocustomers_zr").getRowCount();
		Integer rowcount=(Integer) (obj2==null?"":obj2);
		if(rowcount>0){
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "isnew", true);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "tdate", true);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "ttime", true);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "ttype", true);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "recorder", true);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr").setCellEditable(rowcount-1, "tcustomertype", true);
			Object tcus=billform.getBillCardPanel().getBillModel("id_pocustomers_zr").getValueAt(rowcount-1, "tcustomertype");
			String tcustomer=tcus==null?"暂无跟踪状态":tcus.toString();
			billform.getBillCardPanel().setHeadItem("customert", tcustomer);
		}
		if(rowcount==0){
			billform.getBillCardPanel().setHeadItem("customert", "暂无跟踪状态");
		}
	}
	

}
