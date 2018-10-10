package nc.ui.zl.ly_hflist.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILy_zylistMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_zylist.AggZylistVO;

public class HflistFromZylistQueryService implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggZylistVO[] zylists = null;
		ILy_zylistMaintain service = NCLocator.getInstance().lookup(
				ILy_zylistMaintain.class);
		try {
			zylists=service.query2(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return zylists;
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		return null;

	}

}
