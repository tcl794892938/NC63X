package nc.impl.zl;

import nc.impl.pub.ace.AceHql_familyfilePubServiceImpl;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;
import nc.itf.zl.IHql_familyfileMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Hql_familyfileMaintainImpl extends AceHql_familyfilePubServiceImpl implements
		IHql_familyfileMaintain {

	@Override
	public void delete(AggFamilyfileVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggFamilyfileVO[] insert(AggFamilyfileVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggFamilyfileVO[] update(AggFamilyfileVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggFamilyfileVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
