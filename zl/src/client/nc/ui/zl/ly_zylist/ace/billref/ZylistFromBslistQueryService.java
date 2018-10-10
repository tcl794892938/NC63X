package nc.ui.zl.ly_zylist.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_bslistMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_bslist.AggBslistVO;

public class ZylistFromBslistQueryService implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggBslistVO[] bslists = null;
		ILy_bslistMaintain service = NCLocator.getInstance().lookup(
				ILy_bslistMaintain.class);
		try {
			bslists=service.query2(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return bslists;
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		return null;

	}

}
