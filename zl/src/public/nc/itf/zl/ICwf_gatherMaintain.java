package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.pub.BusinessException;

public interface ICwf_gatherMaintain {

	public void delete(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] insert(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] update(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggGatherVO[] save(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] unsave(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] approve(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;

	public AggGatherVO[] unapprove(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException;
}
