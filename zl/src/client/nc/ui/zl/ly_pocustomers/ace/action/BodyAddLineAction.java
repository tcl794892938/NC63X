package nc.ui.zl.ly_pocustomers.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.ICallBackEvent;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardPanelEventTransformer;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pubapp.AppContext;

public class BodyAddLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	private ShowUpableBillForm billform;

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj1 = getBillform().getBillCardPanel()
				.getBillTable("id_pocustomers_zr").getRowCount();
		Integer rowcount1 = (Integer) (obj1 == null ? "" : obj1);
		Object tcus=getBillform().getBillCardPanel().getBillModel("id_pocustomers_zr").getValueAt(rowcount1-1, "tcustomertype");
		String tcustomer=tcus==null?"":tcus.toString();
		Object obj2=getBillform().getBillCardPanel().getCurrentBodyTableCode();
		String currcode=obj2==null?"":obj2.toString();
		if(tcustomer.equals("")&&rowcount1>0&&currcode.equals("id_pocustomers_zr")){
			MessageDialog.showHintDlg(billform, "提示", "当前行有客户跟踪状态没填，不可新增行！");
			return;
		}
		
		super.doAction(e);
		
		String user = AppContext.getInstance().getPkUser();
		Object obj = getBillform().getBillCardPanel()
				.getBillTable("id_pocustomers_zr").getRowCount();
		Integer rowcount = (Integer) (obj == null ? "" : obj);
		if (rowcount > 1) {
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "isnew", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "tdate", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "ttime", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "ttype", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "recorder", false);
			billform.getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setCellEditable(rowcount - 2, "tcustomertype", false);
		}
		getBillform().getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setValueAt(user, rowcount - 1, "recorder");
		
		getBillform().getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setValueAt(AppContext.getInstance().getBusiDate(), rowcount - 1, "tdate");
		
		getBillform().getBillCardPanel().getBillModel("id_pocustomers_zr")
					.setValueAt(AppContext.getInstance().getServerTime(), rowcount - 1, "ttime");
		
		BillModel bm = billform.getBillCardPanel().getBillModel();
		bm.loadLoadRelationItemValue();
		
	}

}
