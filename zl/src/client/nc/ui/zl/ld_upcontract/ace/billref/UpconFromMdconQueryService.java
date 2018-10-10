package nc.ui.zl.ld_upcontract.ace.billref;

import nc.bs.framework.common.NCLocator;
import nc.itf.zl.ILd_mdcontractMaintain;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

public class UpconFromMdconQueryService implements IRefQueryService{

		@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggMdcontractVO[] mdcontracts = null;
		ILd_mdcontractMaintain service = NCLocator.getInstance().lookup(ILd_mdcontractMaintain.class);
		
		try {
			mdcontracts = service.query2(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return mdcontracts;
	}
	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		// TODO 自动生成的方法存根
		return null;
	}


}
