package nc.ui.zl.hql_contracttype.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.itf.zl.IHql_contracttypeMaintain;
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
public class AceHql_contracttypeMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		IHql_contracttypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_contracttypeMaintain.class);
		return operator.insert((ContracttypeVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		IHql_contracttypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_contracttypeMaintain.class);
		return operator.update((ContracttypeVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		IHql_contracttypeMaintain operator = NCLocator.getInstance().lookup(
				IHql_contracttypeMaintain.class);
		operator.delete((ContracttypeVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		IHql_contracttypeMaintain query = NCLocator.getInstance().lookup(
				IHql_contracttypeMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
