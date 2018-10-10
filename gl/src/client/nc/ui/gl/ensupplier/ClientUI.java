package nc.ui.gl.ensupplier;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.trade.bill.ICardController;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.button.IBillButton;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;
import nc.vo.pub.bill.BillRendererVO;
import nc.vo.trade.button.ButtonVO;

public class ClientUI extends BillCardUI {

	public ClientUI() {
		
	}

	protected ICardController createController() {
		return new ClientUICtrl();
	}

	protected BusinessDelegator createBusinessDelegator() {
		return new MyDelegator();
	}

	protected CardEventHandler createEventHandler() {
		return new MyEventHandler(this, this.getUIControl());
	}

	public String getRefBillType() {
		return null;
	}

	protected void initSelfData() {
		
		this.getButtonManager().getButton(IBillButton.ImportBill).setName("启用");
		this.getButtonManager().getButton(IBillButton.ImportBill).setHint("启用银行账户");
		this.getButtonManager().getButton(IBillButton.ExportBill).setName("停用");
		this.getButtonManager().getButton(IBillButton.ExportBill).setHint("停用银行账户");
	}

	public void setDefaultData() throws Exception {
		
	}

	protected void initPrivateButton() {
		
	}
	
	@Override
	public boolean onClosing() {
		return true;
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		
	}

	@Override
	public boolean beforeEdit(BillEditEvent e) {
		
		return super.beforeEdit(e);
	}

}
