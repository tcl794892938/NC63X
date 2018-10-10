package nc.ui.zl.base_project.ace.actions;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;

public class CalAction extends NCAction {
	
	public CalAction(){
		this.setBtnName("жиЫу");
		this.setCode("calactionssasda");
	}
	
	private ShowUpableBillForm bill;

	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {

		//System.out.println("123123");
		
		Object obj=bill.getBillCardPanel().getHeadItem("nhomeholds").getValueObject();
		Object obj2=bill.getBillCardPanel().getHeadItem("nshopholds").getValueObject();
		
		Integer it = obj==null?0:Integer.valueOf(obj.toString());
		
		Integer it2 = obj2==null?0:Integer.valueOf(obj2.toString());
		
		bill.getBillCardPanel().setHeadItem("nhlods", it+it2);
	}

}
