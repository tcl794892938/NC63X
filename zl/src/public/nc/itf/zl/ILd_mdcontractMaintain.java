package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.pub.BusinessException;

public interface ILd_mdcontractMaintain {

	public void delete(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] insert(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] update(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggMdcontractVO[] save(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] unsave(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] approve(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;

	public AggMdcontractVO[] unapprove(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException;
	
	public AggMdcontractVO[] query2(IQueryScheme queryScheme)
			throws BusinessException ;
	
	public AggMdcontractVO queryHTbyPK(String pk) throws BusinessException;
}
