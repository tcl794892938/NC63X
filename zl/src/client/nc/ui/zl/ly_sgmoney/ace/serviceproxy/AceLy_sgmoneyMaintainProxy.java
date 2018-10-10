package nc.ui.zl.ly_sgmoney.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_sgmoneyMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_sgmoneyMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_sgmoneyMaintain query = NCLocator.getInstance().lookup(
				ILy_sgmoneyMaintain.class);
		return query.query(queryScheme);
	}

}