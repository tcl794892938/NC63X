package nc.ui.zl.ld_kpregister.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ICwf_recbillMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_recbill.AggRecbillVO;

public class KpregisFromRecbillQueryService implements IRefQueryService{

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggRecbillVO[] retbills = null;
		ICwf_recbillMaintain service = NCLocator.getInstance().lookup(
				ICwf_recbillMaintain.class);
		try {
			retbills=service.query3(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

}
