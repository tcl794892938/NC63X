package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.BusinessException;

public interface ILy_carfilesMaintain {

	public void delete(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] insert(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] update(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggCarFilesVO[] save(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] unsave(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] approve(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;

	public AggCarFilesVO[] unapprove(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException;
}
