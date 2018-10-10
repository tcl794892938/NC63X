package nc.impl.zl;

import nc.impl.pub.ace.AceLd_upcontractPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;
import nc.vo.pub.BusinessException;

public class Ld_upcontractMaintainImpl extends AceLd_upcontractPubServiceImpl
		implements ILd_upcontractMaintain {

	@Override
	public void delete(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] insert(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] update(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggUpcontractVO[] save(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] unsave(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] approve(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggUpcontractVO[] unapprove(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
