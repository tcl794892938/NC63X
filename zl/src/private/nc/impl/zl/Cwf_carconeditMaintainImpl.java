package nc.impl.zl;

import nc.impl.pub.ace.AceCwf_carconeditPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.zl.cwf_carconedit.CarconeditBVO;
import nc.vo.zl.cwf_carconedit.CarconeditVO;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.itf.zl.ICwf_carconeditMaintain;
import nc.vo.pub.BusinessException;

public class Cwf_carconeditMaintainImpl extends AceCwf_carconeditPubServiceImpl
		implements ICwf_carconeditMaintain {

	@Override
	public void delete(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] insert(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] update(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggCarconeditVO[] save(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] unsave(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] approve(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {		
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggCarconeditVO[] unapprove(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
