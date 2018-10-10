package nc.ui.zl.hql_customertype.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.zl.hql_customertype.CustomertypeVO;
import nc.itf.zl.IHql_customertypeMaintain;
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
public class AceHql_customertypeMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		IHql_customertypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_customertypeMaintain.class);
		return operator.insert((CustomertypeVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		IHql_customertypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_customertypeMaintain.class);
		return operator.update((CustomertypeVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		IHql_customertypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_customertypeMaintain.class);
		operator.delete((CustomertypeVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		IHql_customertypeMaintain query = NCLocator.getInstance().lookup(
				IHql_customertypeMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
