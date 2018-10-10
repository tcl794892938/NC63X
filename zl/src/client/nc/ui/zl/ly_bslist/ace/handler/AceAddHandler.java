package nc.ui.zl.ly_bslist.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.BusinessException;
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
		try {
			String sql="select pk_base_doc from sm_user where nvl(dr,0)=0 and cuserid='"+AppContext.getInstance().getPkUser()+"'";
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			Object pk_psndoc=iQ.executeQuery(sql, new ColumnProcessor());
			panel.setHeadItem("acceptman", pk_psndoc);
			String sql1="select pk_dept from bd_psnjob where nvl(dr,0)=0 and pk_psndoc='"+pk_psndoc+"'";
			Object pk_dept=iQ.executeQuery(sql1, new ColumnProcessor());
			panel.setHeadItem("acceptbm", pk_dept);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("liststate", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
	}
	
	
}
