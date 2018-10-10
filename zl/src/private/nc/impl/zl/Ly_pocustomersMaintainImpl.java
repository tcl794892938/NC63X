package nc.impl.zl;

import nc.impl.pub.ace.AceLy_pocustomersPubServiceImpl;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;
import nc.itf.zl.ILy_pocustomersMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Ly_pocustomersMaintainImpl extends AceLy_pocustomersPubServiceImpl implements
		ILy_pocustomersMaintain {

	@Override
	public void delete(AggPocustomersVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggPocustomersVO[] insert(AggPocustomersVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggPocustomersVO[] update(AggPocustomersVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggPocustomersVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
