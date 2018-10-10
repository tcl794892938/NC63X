package nc.ui.zl.ld_mdcontract.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.BusinessException;
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
		
		
		//设置单据类型
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode='H440'";
		Object pk_billtype = null;
		try {
			pk_billtype = iQ
					.executeQuery(sql, new ColumnProcessor());
		} catch (BusinessException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		panel.setHeadItem("pk_billtype",pk_billtype );
		panel.setHeadItem("vbilltypecode","H440" );
		
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("state", BillStatusEnum.FREE.value());
	}
}
