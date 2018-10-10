package nc.ui.zl.tcl_contract.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ITcl_contractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceTcl_contractMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ITcl_contractMaintain query = NCLocator.getInstance().lookup(
				ITcl_contractMaintain.class);
		return query.query(queryScheme);
	}

}