package nc.ui.zl.ld_formattype.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;

public class AceAddHandler implements IAppEventHandler<AddEvent> {


	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		//String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		
		String user = e.getContext().getPk_loginUser();
		UFDate dt = AppContext.getInstance().getBusiDate();
		UFDateTime dt2 = AppContext.getInstance().getServerTime();
		
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", "0001A910000000000LRO");
		
		//制单日期
		panel.setHeadItem("dbilldate",dt);
		//创建人
		panel.setTailItem("creator",user);
		//创建日期
		panel.setTailItem("creationtime",dt2);
		
		panel.setHeadItem("vdef1", "0");
		
		//树卡
		
		BillItem parentCode = e.getBillForm().getBillCardPanel().getHeadItem("upname");
		FormattypeVO vo = new FormattypeVO();
		
		if(e.getBillForm().getModel().getSelectedData() != null){
			
			vo = (FormattypeVO)e.getBillForm().getModel().getSelectedData();
			//((UIRefPane)parentCode.getComponent()).setPK(vo.getPrimaryKey());
			
			panel.setHeadItem("upname", vo.pk_formattype);
			panel.setHeadItem("code", vo.getCode());
			//parentCode.setEdit(false);
		}
		
		
		//code = vo.getCode();
	}
}
