package nc.impl.zl;

import nc.impl.pub.ace.AceLm_customerPubServiceImpl;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.itf.zl.ILm_customerMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Lm_customerMaintainImpl extends AceLm_customerPubServiceImpl implements
		ILm_customerMaintain {

	@Override
	public void delete(AggCustomerVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggCustomerVO[] insert(AggCustomerVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggCustomerVO[] update(AggCustomerVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggCustomerVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
