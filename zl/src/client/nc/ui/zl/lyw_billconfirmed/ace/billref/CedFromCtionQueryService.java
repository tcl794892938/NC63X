package nc.ui.zl.lyw_billconfirmed.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILyw_confirmationMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

public class CedFromCtionQueryService implements IRefQueryService {

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		// TODO 自动生成的方法存根
		AggConfirmationVO[] retbills = null;
		ILyw_confirmationMaintain service = NCLocator.getInstance().lookup(ILyw_confirmationMaintain.class);
		try {
			retbills=service.querytoconfirm(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

}
