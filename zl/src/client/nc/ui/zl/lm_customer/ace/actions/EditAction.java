package nc.ui.zl.lm_customer.ace.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.vo.zl.lm_customer.CustomerVO;

public class EditAction extends nc.ui.pubapp.uif2app.actions.EditAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4212813595942459376L;
	private ShowUpableBillForm billform;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		super.doAction(e);
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		AggCustomerVO aggvo=(AggCustomerVO) this.billform.getModel().getSelectedData();
		CustomerVO vo=aggvo.getParentVO();
		String sql="select count(*) from zl_contract where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"' and htstatus<>4";
		String sql1="select count(*) from zl_mdcontract where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"'";
		String sql2="select count(*) from zl_carfiles where nvl(dr,0)=0 and khname='"+vo.getPk_customer()+"'";
		String sql3="select count(*) from zl_prepayment_b where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"'";
		String sql4="select count(*) from zl_payment_b where nvl(dr,0)=0 and pk_customer='"+vo.getPk_customer()+"'";
		String sql5="select count(*) from zl_gather where nvl(dr,0)=0 and isadd='Y' and pk_customer='"+vo.getPk_customer()+"'";
		
		Integer a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
		Integer a1=(Integer) iQ.executeQuery(sql1, new ColumnProcessor());
		Integer a2=(Integer)iQ.executeQuery(sql2, new ColumnProcessor());
		Integer a3=(Integer)iQ.executeQuery(sql3, new ColumnProcessor());
		Integer a4=(Integer)iQ.executeQuery(sql4, new ColumnProcessor());
		Integer a5=(Integer)iQ.executeQuery(sql5, new ColumnProcessor());
		if(a>0||a1>0||a2>0||a3>0||a4>0||a5>0){
			billform.getBillCardPanel().getHeadItem("customername").setEnabled(false);
		}
		
		billform.getBillCardPanel().getBillModel("pk_customerfcxx").setEnabledAllItems(false);
		billform.getBillCardPanel().getBillModel("pk_customerqjfy").setEnabledAllItems(false);
		billform.getBillCardPanel().getBillModel("pk_customerwxfw").setEnabledAllItems(false);
		billform.getBillCardPanel().getBillModel("pk_customercar").setEnabledAllItems(false);
		Object rows = getBillform().getBillCardPanel().getBillTable("pk_customerzzxm").getRowCount();
		Integer rowcount = (Integer) (rows == null ? "" : rows);
		if(rowcount>0){
			for(int i=0;i<rowcount;i++){
				// 获取表体某个页签中的单个值
				Object vdef1 = getBillform().getBillCardPanel().getBillModel("pk_customerzzxm").getValueAt(i, "vdef1");
				String s = vdef1 == null ? "" : vdef1.toString();
				if(!s.equals("")){
					billform.getBillCardPanel().getBillModel("pk_customerzzxm").setCellEditable(i, "pk_project", false);
				}
			}
		}
	}

	public ShowUpableBillForm getBillform() {
		return billform;
	}

	public void setBillform(ShowUpableBillForm billform) {
		this.billform = billform;
	}

	@Override
	protected boolean isActionEnable() {
		Object[] obj=((BillManageModel)getModel()).getSelectedOperaDatas();
		if(obj==null ||obj.length>1){
			return false;
		}
		return true;
	}

}
