package nc.ui.zl.ld_parkcontract.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_parkcontractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_parkcontractMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILd_parkcontractMaintain query = NCLocator.getInstance().lookup(
				ILd_parkcontractMaintain.class);
		return query.query(queryScheme);
	}

}