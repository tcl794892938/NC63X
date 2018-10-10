package nc.ui.zl.cwf_gather.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
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
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("vbillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", new UFDate());
		//panel.setHeadItem("firstbilltype", "0001ZZ1000000001RY1W");
		panel.setHeadItem("isadd", new UFBoolean(true));
		//panel.setHeadItem("is_voucher", new UFBoolean(false));
		panel.setHeadItem("is_kjpt", new UFBoolean(false));
	}
}
