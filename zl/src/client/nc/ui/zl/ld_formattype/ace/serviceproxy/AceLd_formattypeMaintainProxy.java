package nc.ui.zl.ld_formattype.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.itf.zl.ILd_formattypeMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.uif2.LoginContext;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_formattypeMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		ILd_formattypeMaintain operator = NCLocator.getInstance().lookup(
				ILd_formattypeMaintain.class);
		return operator.insert((FormattypeVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		ILd_formattypeMaintain operator = NCLocator.getInstance().lookup(
				ILd_formattypeMaintain.class);
		return operator.update((FormattypeVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		ILd_formattypeMaintain operator = NCLocator.getInstance().lookup(
				ILd_formattypeMaintain.class);
		operator.delete((FormattypeVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		ILd_formattypeMaintain query = NCLocator.getInstance().lookup(
				ILd_formattypeMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
