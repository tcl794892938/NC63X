package nc.ui.zl.ld_customer_owner.ace.actions;
import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.action.QueryExecutor;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_sales.SalesVO;
public class QueryAction extends DefaultQueryAction{

	private QueryExecutor queryExecutor;
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {
			IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
			
			FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
			
			String where = obj2.getWhere(); 
			String user = AppContext.getInstance().getPkUser();
			String sqlcode="select code from zl_customertype where nvl(dr,0)=0 and name='业主'";
			Object code=iQ.executeQuery(sqlcode, new ColumnProcessor());
			String wherenew = where.substring(0, where.length())+" and customertype in " +
					"(select pk_customertype from zl_customertype where nvl(dr,0)=0 and code like '"+getStgObj(code)+"%')";
			String usersql = "select * from zl_sales where username=(select pk_base_doc from sm_user where cuserid='"+user+"') ";
			SalesVO vo=(SalesVO)iQ.executeQuery(usersql, new BeanProcessor(SalesVO.class));
			
			if(vo!=null){
				wherenew += " and zl_customer.zygw='"+vo.getUsername()+"'";
				obj2.setWhere(wherenew);
			}
			
			String pros = "";
			//用户表的身份字段是人员基本信息表的主键
			String get_pk_pro="select c.pk_project from zl_projectcontrol c where nvl(c.dr,0)=0 and c.pk_projectcontrol in " +
					"(select b.pk_projectcontrol from zl_projectcontrol_b b where nvl(b.dr,0)=0 and " +
					"b.usercode=(select s.pk_base_doc from sm_user s where nvl(s.dr,0)=0 and s.cuserid='"+AppContext.getInstance().getPkUser()+"'))";
			List<Object> pk_pro=(List<Object>) iQ.executeQuery(get_pk_pro, new ArrayListProcessor());
			if(pk_pro.size()>0){
				for(int i=0;i<pk_pro.size();i++){
					Object[] pks=(Object[]) pk_pro.get(i);
					pros+="'"+getStgObj(pks[0])+"'";
					if(i<pk_pro.size()-1){
						pros+=",";
					}
				}
			}
			if(pros!=""){
				wherenew += " and exists (select b.* from zl_customer_zzxm b " +
						"where nvl(b.dr,0)=0 and b.pk_customer=zl_customer.pk_customer and b.pk_project in ("+pros+"))";
				obj2.setWhere(wherenew);
			}
			obj2.setWhere(wherenew);
			this.queryExecutor.doQuery(queryScheme);
			afterProcessQuery(queryScheme);
		}	
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}
	
	public QueryAction() {
		ActionInitializer.initializeAction(this, IActionCode.QUERY);
		this.queryExecutor = new QueryExecutor(this);
	}
	
	
	
	
	
	
	
	/*@Override
	public void doAction(ActionEvent e) throws Exception {
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK){
			String user = AppContext.getInstance().getPkUser();
			System.out.println(user);
		}*/
		/*IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
		
		FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
		
		String from = obj2.getFrom();
		String where = obj2.getWhere(); 
		String user = AppContext.getInstance().getPkUser();
		
		System.out.println(user);*/
		/*String sql1 = "select * from zl_sales a where a.username='"+user+"'";
		String sql="select yx_house.* from "+from+" where nvl(yx_house.dr,0)=0 and "+where+" and not exists "
				+ "(select 1 from yx_contract t where t.pk_house=yx_house.pk_house and nvl(t.dr,0)=0 and t.fbillflag=1 ) "
				+ " order by housecode ";

		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		List<AggCustomerVO> clist=null;
		try {
			clist=(List<AggCustomerVO>)iQ.executeQuery(sql, new BeanListProcessor(AggCustomerVO.class));
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		if(clist.size()>0){
			getModel().initModel(clist.toArray(new AggCustomerVO[0]));
		}else{
			getModel().initModel(null);
		}

		super.doAction(e);
	}
	

	
	public ShowUpableBillListView getBilllist() {
		return billlist;
	}
	public void setBilllist(ShowUpableBillListView billlist) {
		this.billlist = billlist;
	}*/
}
