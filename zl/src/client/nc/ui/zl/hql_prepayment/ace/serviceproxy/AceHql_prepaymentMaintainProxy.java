package nc.ui.zl.hql_prepayment.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.IHql_prepaymentMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_prepaymentMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_prepaymentMaintain query = NCLocator.getInstance().lookup(
				IHql_prepaymentMaintain.class);
		return query.query(queryScheme);
	}

}