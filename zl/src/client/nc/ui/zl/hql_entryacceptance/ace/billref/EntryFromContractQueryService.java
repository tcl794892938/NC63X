package nc.ui.zl.hql_entryacceptance.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ITcl_contractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.tcl_contract.AggContractVO;

public class EntryFromContractQueryService implements IRefQueryService {

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		// TODO 自动生成的方法存根
		AggContractVO[] retbills = null;
		ITcl_contractMaintain service = NCLocator.getInstance().lookup(ITcl_contractMaintain.class);
		try {
			retbills=service.queryforJC(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

}
