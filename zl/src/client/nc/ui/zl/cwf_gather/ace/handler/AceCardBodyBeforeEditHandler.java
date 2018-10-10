package nc.ui.zl.cwf_gather.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardBodyBeforeEditHandler implements IAppEventHandler<CardBodyBeforeEditEvent>{

	private ShowUpableBillForm billForm;
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		
		e.setReturnValue(true);
		BillModel bmodel=billForm.getBillCardPanel().getBillModel();
		
		if(e.getKey().equals("pk_building")){
			
			Object pk_proj=billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			Object pk_cust=billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel().getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and pk_projectid='"+pk_proj+"' and pk_buildingfile in (" +
					"select buildnum from zl_customer_fcxx where nvl(dr,0)=0 and pk_customer='"+pk_cust+"')");
		}
		
		if(e.getKey().equals("pk_house")){
			
			//Object objbuild=bmodel.getValueAt(e.getRow(), "pk_building");
			//UIRefPane refp2=(UIRefPane)billForm.getBillCardPanel().getBillModel().getItemByKey("pk_building").getComponent();
			Object pk_cust=billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			//Object objbuild=refp2.getRefPKs()[e.getRow()];
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel().getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and pk_housesource in (select fcname from " +
					" zl_customer_fcxx where nvl(dr,0)=0 and pk_customer='"+pk_cust+"')" );
			
			
		}
		
		if(e.getKey().equals("pk_costproject")){
			
			Object obj=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane refp=(UIRefPane)e.getBillCardPanel().getBillModel().getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().addWherePart(" and (nvl(vdef1,0)=0 or pk_org='"+obj+"')");
		}
		
		if(e.getKey().equals("pk_carno")){
			Object obj=e.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			Object pk_cust=billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
			UIRefPane refp=(UIRefPane)e.getBillCardPanel().getBillModel().getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().addWherePart(" and pk_project='"+obj+"' and pk_carfiles in (select carnum from zl_customer_car" +
					" where nvl(dr,0)=0 and pk_customer='"+pk_cust+"')");
		}
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	private Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}

}
