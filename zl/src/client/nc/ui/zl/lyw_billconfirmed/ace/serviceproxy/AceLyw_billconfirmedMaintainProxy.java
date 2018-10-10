package nc.ui.zl.lyw_billconfirmed.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILyw_billconfirmedMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLyw_billconfirmedMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILyw_billconfirmedMaintain query = NCLocator.getInstance().lookup(
				ILyw_billconfirmedMaintain.class);
		return query.query(queryScheme);
	}

}