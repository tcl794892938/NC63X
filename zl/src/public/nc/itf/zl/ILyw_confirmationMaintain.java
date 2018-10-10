package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.BusinessException;

public interface ILyw_confirmationMaintain {

	public void delete(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] insert(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] update(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	public AggConfirmationVO[] querytoconfirm(IQueryScheme queryScheme)
			throws BusinessException;
	public AggConfirmationVO[] save(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] unsave(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] approve(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;

	public AggConfirmationVO[] unapprove(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException;
}
