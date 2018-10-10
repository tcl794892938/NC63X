package nc.ui.zl.ld_carcontract.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_carcontractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_carcontractMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILd_carcontractMaintain query = NCLocator.getInstance().lookup(
				ILd_carcontractMaintain.class);
		return query.query(queryScheme);
	}

}