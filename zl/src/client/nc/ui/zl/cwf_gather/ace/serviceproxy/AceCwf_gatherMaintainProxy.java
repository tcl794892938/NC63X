package nc.ui.zl.cwf_gather.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ICwf_gatherMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceCwf_gatherMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ICwf_gatherMaintain query = NCLocator.getInstance().lookup(
				ICwf_gatherMaintain.class);
		return query.query(queryScheme);
	}

}