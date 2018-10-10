package nc.ui.zl.ly_bslist.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{

	private ShowUpableBillForm billform;
	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		BillCardPanel panel = billform.getBillCardPanel();
		
		//System.out.println("123");
		if(e.getKey().equals("acceptman")){
			try {
				Object person=panel.getHeadItem("acceptman").getValueObject();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql="select pk_dept from bd_psnjob where nvl(dr,0)=0 and pk_psndoc='"+getStrObj(person)+"'";
				Object pk_dept=iQ.executeQuery(sql, new ColumnProcessor());
				panel.setHeadItem("acceptbm", getStrObj(pk_dept));
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getKey().equals("pk_project")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
				e.getBillCardPanel().setHeadItem("khname", null);
				e.getBillCardPanel().setHeadItem("fcname", null);
				e.getBillCardPanel().setHeadItem("pk_building", null);
			}
			}
		}
		
		if(e.getKey().equals("khname")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
				e.getBillCardPanel().setHeadItem("fcname", null);
				e.getBillCardPanel().setHeadItem("pk_building", null);
			}
			}
		}
		
		if(e.getKey().equals("fcname")){
			try {
				Object pk_fc = billform.getBillCardPanel()
						.getHeadItem(e.getKey()).getValueObject();
				String sql="select buildnum from zl_customer_fcxx where nvl(dr,0)=0 and fcname='"+ getStrObj(pk_fc) + "'";
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Object pk_buildingfile=iQ.executeQuery(sql, new ColumnProcessor());
				panel.setHeadItem("pk_building", getStrObj(pk_buildingfile));
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}

}