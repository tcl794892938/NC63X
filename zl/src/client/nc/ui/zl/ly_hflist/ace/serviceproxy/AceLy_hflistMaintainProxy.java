package nc.ui.zl.ly_hflist.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_hflistMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_hflistMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_hflistMaintain query = NCLocator.getInstance().lookup(
				ILy_hflistMaintain.class);
		return query.query(queryScheme);
	}

}