package nc.ui.zl.ld_housesource.ace.actions;

import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public class QuickSearchAction extends nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell {
	
	private ShowUpableBillListView billlist;
	
	@Override
	public void doLayout() {
		// TODO 自动生成的方法存根
		System.out.println("asd");
		
		
		
		
		super.doLayout();
	}

	protected ShowUpableBillListView getBilllist() {
		return billlist;
	}

	protected void setBilllist(ShowUpableBillListView billlist) {
		this.billlist = billlist;
	}

}
