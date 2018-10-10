package nc.ui.zl.base_project.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent>{
	
	private ShowUpableBillForm billForm;
	

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		if(e.getKey().equals("nhomeholds")){//×¡Õ¬»§Êý
			
			Object obj=e.getValue();
			
			Object obj2=billForm.getBillCardPanel().getHeadItem("nshopholds").getValueObject();
			
			Integer it = obj==null?0:Integer.valueOf(obj.toString());
			
			Integer it2 = obj2==null?0:Integer.valueOf(obj2.toString());
			
			Integer it3=it+it2;
			
			billForm.getBillCardPanel().setHeadItem("nholds", it3);
			
		}
		
	}

}
