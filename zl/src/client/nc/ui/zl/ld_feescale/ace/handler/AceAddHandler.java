package nc.ui.zl.ld_feescale.ace.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		String user = e.getContext().getPk_loginUser();
		
		UFDate dt = AppContext.getInstance().getBusiDate();
		UFDateTime dt2 = AppContext.getInstance().getServerTime();
		
		//System.out.println(user);
		
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// ��������֯Ĭ��ֵ
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		//�Ƶ�����
		panel.setHeadItem("dbilldate",dt);
		//������
		panel.setTailItem("creator",user);
		//��������
		panel.setTailItem("creationtime",dt2);
		
		//�շ���Ŀ
		//panel.setHeadItem("chargeitem",user);
	}
	

    
   
}
