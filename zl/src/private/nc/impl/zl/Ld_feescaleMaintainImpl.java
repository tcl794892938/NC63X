package nc.impl.zl;

import nc.impl.pub.ace.AceLd_feescalePubServiceImpl;
import nc.vo.zl.ld_feescale.AggFeescaleVO;
import nc.itf.zl.ILd_feescaleMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Ld_feescaleMaintainImpl extends AceLd_feescalePubServiceImpl implements
		ILd_feescaleMaintain {

	@Override
	public void delete(AggFeescaleVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggFeescaleVO[] insert(AggFeescaleVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggFeescaleVO[] update(AggFeescaleVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggFeescaleVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
