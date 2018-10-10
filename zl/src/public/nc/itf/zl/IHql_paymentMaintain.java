package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.pub.BusinessException;

public interface IHql_paymentMaintain {

	public void delete(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] insert(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] update(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggPaymentVO[] save(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] unsave(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] approve(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;

	public AggPaymentVO[] unapprove(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException;
}
