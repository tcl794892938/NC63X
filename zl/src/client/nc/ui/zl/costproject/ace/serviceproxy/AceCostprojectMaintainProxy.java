package nc.ui.zl.costproject.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.itf.zl.ICostprojectMaintain;
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
public class AceCostprojectMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		ICostprojectMaintain operator = NCLocator.getInstance().lookup(
				ICostprojectMaintain.class);
		return operator.insert((CostprojectVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		ICostprojectMaintain operator = NCLocator.getInstance().lookup(
				ICostprojectMaintain.class);
		return operator.update((CostprojectVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		ICostprojectMaintain operator = NCLocator.getInstance().lookup(
				ICostprojectMaintain.class);
		operator.delete((CostprojectVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		ICostprojectMaintain query = NCLocator.getInstance().lookup(
				ICostprojectMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
