package nc.ui.zl.hql_entryacceptance.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
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
		// ��������֯Ĭ��ֵ
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		panel.setHeadItem("dbilldate", new UFDate());
		// ���õ���״̬������ҵ������Ĭ��ֵ
		panel.setHeadItem("vbillstatus", BillStatusEnum.FREE.value());
	}
}
