package nc.impl.zl;

import nc.impl.pub.ace.AceLd_housesourcePubServiceImpl;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.itf.zl.ILd_housesourceMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Ld_housesourceMaintainImpl extends AceLd_housesourcePubServiceImpl implements
		ILd_housesourceMaintain {

	@Override
	public void delete(AggHousesourceVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggHousesourceVO[] insert(AggHousesourceVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggHousesourceVO[] update(AggHousesourceVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggHousesourceVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
