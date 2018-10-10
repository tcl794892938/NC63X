package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.BusinessException;

public interface IHql_throwaleaseMaintain {

	public void delete(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] insert(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] update(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggThrowaleaseVO[] save(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] unsave(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] approve(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;

	public AggThrowaleaseVO[] unapprove(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException;
}
