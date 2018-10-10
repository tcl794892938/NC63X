package nc.ui.zl.hql_entryacceptance.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.IHql_entryacceptanceMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_entryacceptanceMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_entryacceptanceMaintain query = NCLocator.getInstance().lookup(
				IHql_entryacceptanceMaintain.class);
		return query.query(queryScheme);
	}

}