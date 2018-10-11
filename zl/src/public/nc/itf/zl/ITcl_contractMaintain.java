package nc.itf.zl;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.pub.BusinessException;

public interface ITcl_contractMaintain {

	public void delete(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] insert(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] update(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggContractVO[] save(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] unsave(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] approve(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;

	public AggContractVO[] unapprove(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException;
	
	public AggContractVO[] queryHTforXD(IQueryScheme queryScheme)
			throws BusinessException;
	
	/**
	 * 根据pk查询合同
	 */
	public AggContractVO queryHTbyPK(String pk) throws BusinessException;
	public List<AggContractVO> queryHTbyPK2(List<String> pks) throws BusinessException;
	
	public AggContractVO[] queryforJC(IQueryScheme queryScheme)
			throws BusinessException;
	
	public AggContractVO[] queryforTC(IQueryScheme queryScheme)
			throws BusinessException;
}
