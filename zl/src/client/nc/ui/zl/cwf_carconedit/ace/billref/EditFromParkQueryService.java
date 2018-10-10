package nc.ui.zl.cwf_carconedit.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.itf.zl.ILd_parkcontractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;

public class EditFromParkQueryService implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggParkcontractVO[] retbills = null;
		ILd_parkcontractMaintain service = NCLocator.getInstance().lookup(
				ILd_parkcontractMaintain.class);
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
