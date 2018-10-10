package nc.impl.zl;

import nc.impl.pub.ace.AceCwf_salesPubServiceImpl;
import nc.vo.zl.cwf_sales.AggSalesVO;
import nc.itf.zl.ICwf_salesMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Cwf_salesMaintainImpl extends AceCwf_salesPubServiceImpl implements
		ICwf_salesMaintain {

	@Override
	public void delete(AggSalesVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggSalesVO[] insert(AggSalesVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggSalesVO[] update(AggSalesVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggSalesVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
