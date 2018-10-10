package nc.ui.zl.hql_contracttype.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.zl.hql_customertype.CustomertypeVO;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		
		// ��������֯Ĭ��ֵ
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		//�����Ƶ�����
		panel.setHeadItem("dbilldate", new UFDate());
		// ���ô����˵�ֵ
		panel.setTailItem("creator", e.getContext().getPk_loginUser());
		//���ô���ʱ��
		panel.setTailItem("creationtime", new UFDateTime());
		//�����ϼ����Ƶ�ֵ
		BillItem parentCode = e.getBillForm().getBillCardPanel()
				.getHeadItem("pk_parentid");
		if(e.getBillForm().getModel().getSelectedData() != null){
			ContracttypeVO vo = (ContracttypeVO) e.getBillForm().getModel()
					.getSelectedData();
			((UIRefPane)parentCode.getComponent()).setPK(vo.getPrimaryKey());
			panel.setHeadItem("code", vo.getCode());
			//parentCode.setValue(vo.getPrimaryKey());
			//parentCode.setEdit(false);
		}
	}
}
