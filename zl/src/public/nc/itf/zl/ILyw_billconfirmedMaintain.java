package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.pub.BusinessException;

public interface ILyw_billconfirmedMaintain {

	public void delete(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] insert(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] update(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBillconfirmedVO[] save(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] unsave(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] approve(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;

	public AggBillconfirmedVO[] unapprove(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException;
}
