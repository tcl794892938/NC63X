package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.pub.BusinessException;

public interface ICwf_recbillMaintain {

	public void delete(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] insert(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] update(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	
	public AggRecbillVO[] query2(IQueryScheme queryScheme)
			throws BusinessException;
	
	public AggRecbillVO[] query3(IQueryScheme queryScheme)
			throws BusinessException;

	public AggRecbillVO[] save(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] unsave(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] approve(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;

	public AggRecbillVO[] unapprove(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException;
}
