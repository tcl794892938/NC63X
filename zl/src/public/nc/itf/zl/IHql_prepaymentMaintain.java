package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.BusinessException;

public interface IHql_prepaymentMaintain {

	public void delete(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] insert(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] update(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggPrepaymentVO[] save(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] unsave(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] approve(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;

	public AggPrepaymentVO[] unapprove(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException;
}
