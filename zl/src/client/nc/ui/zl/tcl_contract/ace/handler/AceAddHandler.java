package nc.ui.zl.tcl_contract.ace.handler;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("vbillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", new UFDate());
		panel.setHeadItem("htstatus", AbsEnumType.HTstatus1);
		
		if(e.getContext().getNodeCode().equals("ZLH420")){
			panel.setHeadItem("pk_billtype", "0001ZZ1000000001SNDJ");
			panel.setHeadItem("vbilltypecode", "H420");
			panel.setHeadItem("version", -1);
			
			//插入行操作
			panel.getBillModel("pk_contract_cust").addLine();
			int[] rows = new int[]{0};
			e.getBillForm().getModel().fireEvent(new CardBodyAfterRowEditEvent(panel,BodyRowEditType.ADDLINE, rows));
			
		}else if(e.getContext().getNodeCode().equals("ZLH430")){
			panel.setHeadItem("pk_billtype", "0001ZZ1000000001SON1");
			panel.setHeadItem("vbilltypecode", "H430");
		}
	}
}
