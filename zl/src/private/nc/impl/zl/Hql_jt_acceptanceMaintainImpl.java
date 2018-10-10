package nc.impl.zl;

import nc.impl.pub.ace.AceHql_jt_acceptancePubServiceImpl;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;
import nc.itf.zl.IHql_jt_acceptanceMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Hql_jt_acceptanceMaintainImpl extends AceHql_jt_acceptancePubServiceImpl implements
		IHql_jt_acceptanceMaintain {

	@Override
	public void delete(AggJt_acceptanceVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggJt_acceptanceVO[] insert(AggJt_acceptanceVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggJt_acceptanceVO[] update(AggJt_acceptanceVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggJt_acceptanceVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
