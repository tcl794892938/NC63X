package nc.ui.zl.ly_bslist.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_bslistMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_bslistMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_bslistMaintain query = NCLocator.getInstance().lookup(
				ILy_bslistMaintain.class);
		return query.query(queryScheme);
	}

}