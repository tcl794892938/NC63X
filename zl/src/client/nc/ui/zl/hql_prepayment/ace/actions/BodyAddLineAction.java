package nc.ui.zl.hql_prepayment.ace.actions;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;

public class BodyAddLineAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7023118102367844806L;
	private ShowUpableBillForm bill;
	
	public ShowUpableBillForm getBill() {
		return bill;
	}

	public void setBill(ShowUpableBillForm bill) {
		this.bill = bill;
	}

	/*@Override
	public void doAction() {
		super.doAction();
		bill.getBillCardPanel().getBillModel().setValueAt("1001A910000000000006", bill.getBillCardPanel().getBillModel().getRowCount()-1, "caccountperiod");
		bill.getBillCardPanel().getBillModel().setValueAt("1001A910000000000006", bill.getBillCardPanel().getBillModel().getRowCount()-1, "remark");
		UIRefPane refp2=(UIRefPane)bill.getBillCardPanel().getBillModel().getItemByKey("caccountperiod").getComponent();
		
	}
*/
	@Override
	protected void afterLineInsert(int index) {
		// TODO 自动生成的方法存根
//		Object dateObj = bill.getBillCardPanel().getHeadItem("dysdate").getValueObject();
//		if(index >= 0){
//			bill.getBillCardPanel().setBodyValueAt(dateObj, index, "dysdate");
//			if(dateObj != null){
//				try {
//				String d = dateObj.toString();
//				IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
//				String sql = "select pk_accperiodmonth from bd_accperiodmonth where " +
//						     "nvl(dr,0)=0 and (begindate <= '"+d+"' and enddate >= '"+d+"')";
//				
//					Object pk = iQ.executeQuery(sql, new ColumnProcessor());
//				    bill.getBillCardPanel().setBodyValueAt(pk, index, "caccountperiod");
//					//bill.getBillCardPanel().setBodyValueAt(pk, index, "caccountperiod");
//					
//					//System.out.println(e.getBillCardPanel().getBodyValueAt(row, "caccountperiod"));
//				} catch (BusinessException e1) {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}
//			}
//		}
		bill.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		bill.getBillCardPanel().stopEditing();
	}
	
}
