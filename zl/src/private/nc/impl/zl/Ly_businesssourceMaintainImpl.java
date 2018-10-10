package nc.impl.zl;

import nc.impl.pub.ace.AceLy_businesssourcePubServiceImpl;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;
import nc.itf.zl.ILy_businesssourceMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Ly_businesssourceMaintainImpl extends AceLy_businesssourcePubServiceImpl implements
		ILy_businesssourceMaintain {

	@Override
	public void delete(AggBusinessSourceVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggBusinessSourceVO[] insert(AggBusinessSourceVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggBusinessSourceVO[] update(AggBusinessSourceVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggBusinessSourceVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
