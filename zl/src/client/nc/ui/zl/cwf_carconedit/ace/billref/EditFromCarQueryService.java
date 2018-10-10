package nc.ui.zl.cwf_carconedit.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_carcontractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;

public class EditFromCarQueryService implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggCarcontractVO[] retbills = null;
		ILd_carcontractMaintain service = NCLocator.getInstance().lookup(
				ILd_carcontractMaintain.class);
		try {
			retbills=service.query2(queryScheme);
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
