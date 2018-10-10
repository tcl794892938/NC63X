package nc.ui.zl.ld_mdcontract.ace.actions;

import java.awt.event.ActionEvent;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.actions.ActionInitializer;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.action.QueryExecutor;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.AppContext;

public class QueryAction extends DefaultQueryAction{
	
	private QueryExecutor queryExecutor;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		if (this.getQryDLGDelegator().showModal() == UIDialog.ID_OK) {

			
			IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
			
			FromWhereSQLImpl obj2=(FromWhereSQLImpl)queryScheme.getTableJoinFromWhereSQL();
			
			String where = obj2.getWhere() + " and version=-1";
			obj2.setWhere(where);
			this.queryExecutor.doQuery(queryScheme);
			afterProcessQuery(queryScheme);
			
		}
		//super.doAction(e);
	}
	
	public QueryAction() {
		ActionInitializer.initializeAction(this, IActionCode.QUERY);
		this.queryExecutor = new QueryExecutor(this);
	}
	
	public String getStgObj(Object obj){
		return obj==null?"":obj.toString();
	}

}
