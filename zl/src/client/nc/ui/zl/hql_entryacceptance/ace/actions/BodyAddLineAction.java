package nc.ui.zl.hql_entryacceptance.ace.actions;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDate;

public class BodyAddLineAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction {
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	@Override
	protected void afterLineInsert(int index) {
		// TODO �Զ����ɵķ������
		//System.out.println(index);
		UFDate date = new UFDate();
	    //bill.getBillCardPanel().setBodyValueAt(date.getLocalYear()+"��"+date.getLocalMonth(), index, "serialnumber");
		bill.getBillCardPanel().setBodyValueAt(index+1, index, "serialnumber");
		super.afterLineInsert(index);
	}
	
}
