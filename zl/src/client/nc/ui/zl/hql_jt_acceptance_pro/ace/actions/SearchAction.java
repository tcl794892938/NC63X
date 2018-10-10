package nc.ui.zl.hql_jt_acceptance_pro.ace.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;

import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.ShowStatusBarMsgUtil;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;
import nc.vo.zl.hql_jt_acceptance.Jt_acceptanceVO;

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
		StringBuilder where2 = new StringBuilder(where);
		int i = where.indexOf("zl_jt_acceptance.pk_project = '");
		int j=where.indexOf("AND (");
		where2.insert(j, ") ");
		where2.insert(i, "(zl_jt_acceptance.vdef1 = '0' or ");
		where = where2.toString();
		String sql="select * from "+from+" where nvl(zl_jt_acceptance.dr,0)=0 and "+where
				+ " order by code";
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		List<Jt_acceptanceVO> clist=null;
		try {
			clist=(List<Jt_acceptanceVO>)iQ.executeQuery(sql, new BeanListProcessor(Jt_acceptanceVO.class));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		if(clist.size()>0){
			
			List<AggJt_acceptanceVO> aggvos=new ArrayList<AggJt_acceptanceVO>();
			for(Jt_acceptanceVO vo:clist){
				AggJt_acceptanceVO aggvo=new AggJt_acceptanceVO();
				aggvo.setParentVO(vo);
				aggvos.add(aggvo);
				
			}
			getModel().initModel(aggvos.toArray(new AggJt_acceptanceVO[0]));
		}else{
			getModel().initModel(null);
		}
		
		afterProcessQuery(queryScheme);
	}

	private String getStrObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	private UFDouble getUfdObj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}




	public ShowUpableBillListView getBilllist() {
		return billlist;
	}




	public void setBilllist(ShowUpableBillListView billlist) {
		this.billlist = billlist;
	}


	
	
}
