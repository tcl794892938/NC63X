package nc.ui.zl.tcl_costtype.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.zl.tcl_costtype.CosttypeVO;
import nc.itf.zl.ITcl_costtypeMaintain;
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
public class AceTcl_costtypeMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		ITcl_costtypeMaintain operator = NCLocator.getInstance().lookup(
				ITcl_costtypeMaintain.class);
		return operator.insert((CosttypeVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		ITcl_costtypeMaintain operator = NCLocator.getInstance().lookup(
				ITcl_costtypeMaintain.class);
		return operator.update((CosttypeVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		ITcl_costtypeMaintain operator = NCLocator.getInstance().lookup(
				ITcl_costtypeMaintain.class);
		operator.delete((CosttypeVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		ITcl_costtypeMaintain query = NCLocator.getInstance().lookup(
				ITcl_costtypeMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
