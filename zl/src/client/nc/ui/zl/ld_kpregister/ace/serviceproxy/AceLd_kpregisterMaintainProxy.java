package nc.ui.zl.ld_kpregister.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_kpregisterMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_kpregisterMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILd_kpregisterMaintain query = NCLocator.getInstance().lookup(
				ILd_kpregisterMaintain.class);
		return query.query(queryScheme);
	}

}