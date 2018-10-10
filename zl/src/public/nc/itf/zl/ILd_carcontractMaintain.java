package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.pub.BusinessException;

public interface ILd_carcontractMaintain {

	public void delete(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] insert(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] update(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	public AggCarcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException;

	public AggCarcontractVO[] save(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] unsave(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] approve(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;

	public AggCarcontractVO[] unapprove(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException;
	
	public AggCarcontractVO queryCHTbyPK(String pk) throws BusinessException ;
}
