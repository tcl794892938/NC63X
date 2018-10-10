package nc.ui.zl.ld_parkcontract.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.ui.pub.bill.BillCardPanel;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		panel.setHeadItem("rentdate", new UFDate());
		panel.setHeadItem("dbilldate", new UFDate());
		panel.setTailItem("creator", AppContext.getInstance().getPkUser());
		panel.setTailItem("creationtime", new UFDateTime());
		
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("vbillstatus", BillStatusEnum.FREE.value());
		
		panel.setHeadItem("pk_billtype","0001ZZ1000000001UR8P" );
		panel.setHeadItem("vbilltypecode","H523" );
		panel.setHeadItem("version", -1);
	}
}
