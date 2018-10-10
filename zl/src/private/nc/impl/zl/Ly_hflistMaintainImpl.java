package nc.impl.zl;

import nc.impl.pub.ace.AceLy_hflistPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.itf.zl.ILy_hflistMaintain;
import nc.vo.pub.BusinessException;

public class Ly_hflistMaintainImpl extends AceLy_hflistPubServiceImpl
		implements ILy_hflistMaintain {

	@Override
	public void delete(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] insert(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] update(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggHflistVO[] save(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] unsave(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] approve(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggHflistVO[] unapprove(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
