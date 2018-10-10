package nc.ui.zl.hql_payment.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.IHql_paymentMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_paymentMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_paymentMaintain query = NCLocator.getInstance().lookup(
				IHql_paymentMaintain.class);
		return query.query(queryScheme);
	}

}