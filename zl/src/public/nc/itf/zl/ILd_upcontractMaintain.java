package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.BusinessException;

public interface ILd_upcontractMaintain {

	public void delete(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] insert(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] update(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggUpcontractVO[] save(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] unsave(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] approve(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;

	public AggUpcontractVO[] unapprove(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException;
}
