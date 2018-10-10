package nc.ui.zl.tcl_contract.ace.handler;

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
		String tabcode=billForm.getBillCardPanel().getCurrentBodyTableCode();
		BillModel bmodel=billForm.getBillCardPanel().getBillModel(tabcode);
		
		//======================================================客户页签=============================================================
		if(e.getKey().equals("pk_customer")&&tabcode.equals("pk_contract_cust")){
			
			Object pk_org=billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and exists(select 1 from zl_customer_zzxm m where m.pk_customer=zl_customer.pk_customer and m.pk_project='"+pk_org+"') ");
		}
		
		//======================================================房产页签=============================================================
		if(e.getKey().equals("pk_building")&&tabcode.equals("pk_contract_house")){
			
			Object pk_proj=billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and pk_projectid='"+pk_proj+"'");
		}
		
		if(e.getKey().equals("pk_house")&&tabcode.equals("pk_contract_house")){
			
			BillModel bmodel_d=billForm.getBillCardPanel().getBillModel("pk_contract_house");
			Object objbuild=getColvalue(bmodel_d.getValueObjectAt(e.getRow(), "pk_building"));
			
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and buildname='"+objbuild+"' and housestate<>1");
		}
		if(e.getKey().equals("pk_customer")&&tabcode.equals("pk_contract_house")){
			Object pk_org=billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and exists(select 1 from zl_customer_zzxm m where m.pk_customer=zl_customer.pk_customer and m.pk_project='"+pk_org+"') ");
		}
		//======================================================周期费用页签=============================================================
		if(e.getKey().equals("pk_house")&&tabcode.equals("pk_contract_zqfy")){
			
			//获取房产信息的房子带入
			BillModel bmodel_f=billForm.getBillCardPanel().getBillModel("pk_contract_house");
			String pk_house="";
			for(int i=0;i<bmodel_f.getRowCount();i++){
				Object objhouse=getColvalue(bmodel_f.getValueObjectAt(i, "pk_house"));
				pk_house+="'"+objhouse+"',";
			}
			if(!"".equals(pk_house)){
				pk_house=pk_house.substring(0, pk_house.lastIndexOf(","));
			}
			
			UIRefPane refp=(UIRefPane)billForm.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			refp.getRefModel().addWherePart(" and pk_housesource in("+pk_house+") ");
		}
		
		if(e.getKey().equals("pk_feescale")&&tabcode.equals("pk_contract_zqfy")){
			
			UIRefPane refp=(UIRefPane)bmodel.getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().reloadData();
			Object objcost=getColvalue(bmodel.getValueObjectAt(e.getRow(), "pk_costproject"));
			Object obj=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			refp.getRefModel().addWherePart(" and chargeitem ='"+objcost+"' and pk_org='"+obj+"'");
		}
		
		if(e.getKey().equals("pk_costproject")&&tabcode.equals("pk_contract_zqfy")){
			
			Object obj=e.getBillCardPanel().getHeadItem("pk_org").getValueObject();
			UIRefPane refp=(UIRefPane)e.getBillCardPanel().getBillModel(tabcode).getItemByKey(e.getKey()).getComponent();
			refp.getRefModel().addWherePart(" and (nvl(vdef1,0)='0' or pk_org='"+obj+"')");
			
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
