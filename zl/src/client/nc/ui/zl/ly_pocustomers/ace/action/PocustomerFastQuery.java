package nc.ui.zl.ly_pocustomers.ace.action;

import java.util.List;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pubapp.uif2app.query2.action.QueryExecutor;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.cwf_sales.SalesVO;

public class PocustomerFastQuery {

	private QueryExecutor queryExecutor;

	@SuppressWarnings("unchecked")
	public IQueryScheme doQuery(IQueryScheme queryScheme) throws Exception {

		IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);

		FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
		
		String where = obj2.getWhere(); 
		String user = AppContext.getInstance().getPkUser();
		String wherenew=where.substring(0, where.length());
		String usersql = "select * from zl_sales where nvl(dr,0)=0 and username=(select pk_base_doc from sm_user where cuserid='"+user+"') ";
		SalesVO vo=(SalesVO)iQ.executeQuery(usersql, new BeanProcessor(SalesVO.class));
		if(vo!=null){
			wherenew += " and zl_pocustomers.salesman='"+vo.getUsername()+"'";
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
			wherenew += " and exists (select b.* from zl_pocustomers_org b " +
					"where nvl(b.dr,0)=0 and b.pk_pocustomers=zl_pocustomers.pk_pocustomers and b.procode in ("+pros+"))";
			obj2.setWhere(wherenew);
		}
		return queryScheme;
	}
	
	public QueryExecutor getQueryExecutor() {
		return queryExecutor;
	}

	public void setQueryExecutor(QueryExecutor queryExecutor) {
		this.queryExecutor = queryExecutor;
	}

	public String getStgObj(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	
	
}
