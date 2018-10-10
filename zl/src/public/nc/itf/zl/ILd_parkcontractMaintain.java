package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.BusinessException;

public interface ILd_parkcontractMaintain {

	public void delete(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] insert(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] update(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	public AggParkcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException;

	public AggParkcontractVO[] save(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] unsave(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] approve(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;

	public AggParkcontractVO[] unapprove(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException;
	
	public AggParkcontractVO queryCHTbyPK(String pk)throws BusinessException;
}
