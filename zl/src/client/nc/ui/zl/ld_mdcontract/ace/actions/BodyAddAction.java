package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class BodyAddAction extends nc.ui.pubapp.uif2app.actions.BodyAddLineAction{

	private ShowUpableBillForm billform;
	@Override
	public void doAction() {
		try {
			billform.getBillCardPanel().stopEditing();
			billform.getBillCardPanel().dataNotNullValidate();
		} catch (ValidationException e) {
			MessageDialog.showHintDlg(billform, "提示", e.getMessage());
			return ;
		}
		Object obj = getBillform().getBillCardPanel()
				.getBillTable("pk_mdcontract_c").getRowCount();
		Integer rowcount = (Integer) (obj == null ? "" : obj);
		Object startdate = billform.getBillCardPanel().getHeadItem("startdate").getValueObject();
		Object enddate = billform.getBillCardPanel().getHeadItem("enddate").getValueObject();
		
		Object obj_mend = null;
		if(rowcount>0){
			obj_mend = billform.getBillCardPanel().getBillModel("pk_mdcontract_c").getValueAt(rowcount-1, "menddate");
			if(obj_mend!=null){
				if(getUFdateobj(obj_mend).equals(getUFdateobj(enddate))){
					MessageDialog.showHintDlg(billform, "提示", "费用截止日期已到合同终止日，不可新增收费！");
					return ;
				}
			}
			
		}
		super.doAction();
		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		Object customer = billform.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
		if(customer!=null){		
			if(rowcount==0){
				if(startdate!=null){
					String date = startdate.toString();
					String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+date.substring(0, 7)+"'";
					Object obj_a = null;
					try {
						obj_a=iQ.executeQuery(sql_date, new ColumnProcessor());
					} catch (BusinessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(startdate, 0, "mstartdate");
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(obj_a, 0, "accountmonth");
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(startdate, 0, "moneydate");
				}
				
				
				
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(customer, 0, "pk_customer");
				
			}else{
				if(obj_mend!=null){
					String sql_date = "select pk_accperiodmonth  from bd_accperiodmonth where yearmth='"+obj_mend.toString().substring(0, 7)+"'";
					Object obj_a = null;
					try {
						obj_a=iQ.executeQuery(sql_date, new ColumnProcessor());
					} catch (BusinessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					UFDate date1 = new UFDate(obj_mend.toString());
					Object obj_cost = billform.getBillCardPanel().getBillModel("pk_mdcontract_c").getValueObjectAt(rowcount-1, "pk_costproject");
					Object costproject = ((DefaultConstEnum)obj_cost).getValue();
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(costproject, rowcount, "pk_costproject");
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(getStgObj(date1.getDateAfter(1)), rowcount, "mstartdate");
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(obj_a, rowcount, "accountmonth");
					billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(getStgObj(date1.getDateAfter(1)), rowcount, "moneydate");
				}
				
				
				billform.getBillCardPanel().getBillModel("pk_mdcontract_c").setValueAt(customer, rowcount, "pk_customer");
				
			}
			billform.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
			
		}

		
		
	}
	public UFDate getUFdateobj(Object obj){
		return obj == null ? new UFDate() : new UFDate(obj.toString());
	}
	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	public ShowUpableBillForm getBillform() {
		return billform;
	}
	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}
}
