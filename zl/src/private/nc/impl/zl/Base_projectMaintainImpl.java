package nc.impl.zl;

import nc.impl.pub.ace.AceBase_projectPubServiceImpl;
import nc.vo.zl.base_project.AggProjectVO;
import nc.itf.zl.IBase_projectMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;

public class Base_projectMaintainImpl extends AceBase_projectPubServiceImpl implements
		IBase_projectMaintain {

	@Override
	public void delete(AggProjectVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggProjectVO[] insert(AggProjectVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggProjectVO[] update(AggProjectVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggProjectVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

}
