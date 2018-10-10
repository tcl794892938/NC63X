package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.BusinessException;

public interface IHql_entryacceptanceMaintain {

	public void delete(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] insert(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] update(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggEntryacceptanceVO[] save(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] unsave(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] approve(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;

	public AggEntryacceptanceVO[] unapprove(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException;
}
