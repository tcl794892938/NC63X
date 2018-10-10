package nc.impl.zl;

import nc.impl.pub.ace.AceLy_carfilesPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.itf.zl.ILy_carfilesMaintain;
import nc.vo.pub.BusinessException;

public class Ly_carfilesMaintainImpl extends AceLy_carfilesPubServiceImpl
		implements ILy_carfilesMaintain {

	@Override
	public void delete(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] insert(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] update(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggCarFilesVO[] save(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] unsave(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] approve(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarFilesVO[] unapprove(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
