package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.BusinessException;

public interface ILd_kpregisterMaintain {

	public void delete(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] insert(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] update(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggKpregisterVO[] save(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] unsave(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] approve(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;

	public AggKpregisterVO[] unapprove(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException;
}
