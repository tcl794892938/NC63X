package nc.ui.zl.ld_mdcontract.ace.actions;

import nc.ui.pubapp.uif2app.query2.action.QueryExecutor;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;

public class MdcontractFastQuery {

	private QueryExecutor queryExecutor;

	@SuppressWarnings("unchecked")
	public IQueryScheme doQuery(IQueryScheme queryScheme) throws Exception {

		FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
		
		String where = obj2.getWhere();
		where += " and version = -1";
		obj2.setWhere(where);
		
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
