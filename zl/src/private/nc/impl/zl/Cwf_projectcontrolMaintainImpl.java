package nc.impl.zl;

import nc.impl.pub.ace.AceCwf_projectcontrolPubServiceImpl;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;
import nc.itf.zl.ICwf_projectcontrolMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Cwf_projectcontrolMaintainImpl extends AceCwf_projectcontrolPubServiceImpl implements
		ICwf_projectcontrolMaintain {

	@Override
	public void delete(AggProjectcontrol[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggProjectcontrol[] insert(AggProjectcontrol[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggProjectcontrol[] update(AggProjectcontrol[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggProjectcontrol[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
