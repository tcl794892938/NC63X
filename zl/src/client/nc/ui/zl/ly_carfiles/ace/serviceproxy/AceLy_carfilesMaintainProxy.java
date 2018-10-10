package nc.ui.zl.ly_carfiles.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_carfilesMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_carfilesMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_carfilesMaintain query = NCLocator.getInstance().lookup(
				ILy_carfilesMaintain.class);
		return query.query(queryScheme);
	}

}