package nc.impl.zl;

import nc.impl.pub.ace.AceLy_sgmoneyPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.itf.zl.ILy_sgmoneyMaintain;
import nc.vo.pub.BusinessException;

public class Ly_sgmoneyMaintainImpl extends AceLy_sgmoneyPubServiceImpl
		implements ILy_sgmoneyMaintain {

	@Override
	public void delete(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] insert(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] update(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggSgMoneyVO[] save(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] unsave(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] approve(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggSgMoneyVO[] unapprove(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
