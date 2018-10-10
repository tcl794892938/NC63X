package nc.ui.zl.tcl_contract.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ITcl_contractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.tcl_contract.AggContractVO;

public class H430FromH420QueryService implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggContractVO[] retbills = null;
		ITcl_contractMaintain service = NCLocator.getInstance().lookup(
				ITcl_contractMaintain.class);
		try {
			retbills=service.queryHTforXD(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		return null;

	}

}
