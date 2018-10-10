package nc.ui.zl.hql_throwalease.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.IHql_throwaleaseMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_throwaleaseMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_throwaleaseMaintain query = NCLocator.getInstance().lookup(
				IHql_throwaleaseMaintain.class);
		return query.query(queryScheme);
	}

}