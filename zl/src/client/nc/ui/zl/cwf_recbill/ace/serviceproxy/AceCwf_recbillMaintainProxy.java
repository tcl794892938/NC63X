package nc.ui.zl.cwf_recbill.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceCwf_recbillMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ICwf_recbillMaintain query = NCLocator.getInstance().lookup(
				ICwf_recbillMaintain.class);
		return query.query(queryScheme);
	}

}