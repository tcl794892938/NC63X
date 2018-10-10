package nc.ui.zl.ly_sgmoney.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;

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
		
		if(e.getKey().equals("pk_project")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
					e.getBillCardPanel().setHeadItem("payproject", null);
					e.getBillCardPanel().setHeadItem("khname", null);
					e.getBillCardPanel().setHeadItem("fcname", null);
				}
			}
		}
		
		if(e.getKey().equals("khname")){
			if(e.getOldValue()==null||e.getOldValue().equals("")){
				return;
			}else{
				if(!e.getOldValue().equals(e.getValue())){
					e.getBillCardPanel().setHeadItem("fcname", null);
				}
			}
		}
		
	}

	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}