package nc.ui.zl.hql_payment.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;

import nc.vo.pub.BusinessException;

import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.zl.hql_entryacceptance.EntryacceptanceVO;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.zl.hql_payment.PaymentVO;

public class SearchAction extends DefaultQueryAction {

	private static final long serialVersionUID = 5953276677401427168L;
	
	private ShowUpableBillListView billlist;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
			//this.getQryCondDLGInitializer()
			BillModel hmodel=billlist.getBillListPanel().getHeadBillModel();
			hmodel.clearBodyData();
			this.processQuery2();
			
			if(hmodel.getRowCount()<=0){
				ShowStatusBarMsgUtil.showStatusBarMsg("未查到任何信息！", getModel().getContext());
				return ;
			}
			
		}
		
	}
	
	private void processQuery2() {
		
		IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		
		FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
		
		String from = obj2.getFrom();
		String where = obj2.getWhere();
		
		String sql="select * from "+from+" where nvl(zl_payment.dr,0)=0 and "+where
				+ " order by vbillno";
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		List<PaymentVO> clist=null;
		try {
			clist=(List<PaymentVO>)iQ.executeQuery(sql, new BeanListProcessor(PaymentVO.class));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		if(clist.size()>0){
			
			List<AggPaymentVO> aggvos=new ArrayList<AggPaymentVO>();
			for(PaymentVO vo:clist){
				AggPaymentVO aggvo=new AggPaymentVO();
				aggvo.setParentVO(vo);
				aggvos.add(aggvo);
				
			}
			getModel().initModel(aggvos.toArray(new AggPaymentVO[0]));
		}else{
			getModel().initModel(null);
		}
		
		afterProcessQuery(queryScheme);
	}

	public ShowUpableBillListView getBilllist() {
		return billlist;
	}

	public void setBilllist(ShowUpableBillListView billlist) {
		this.billlist = billlist;
	}

}
