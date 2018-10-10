package nc.ui.zl.lyw_confirmation.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLyw_confirmationMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILyw_confirmationMaintain query = NCLocator.getInstance().lookup(
				ILyw_confirmationMaintain.class);
		return query.query(queryScheme);
	}

}