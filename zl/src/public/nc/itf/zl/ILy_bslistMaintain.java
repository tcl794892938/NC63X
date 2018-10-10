package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.pub.BusinessException;

public interface ILy_bslistMaintain {

	public void delete(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] insert(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] update(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	
	public AggBslistVO[] query2(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBslistVO[] save(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] unsave(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] approve(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;

	public AggBslistVO[] unapprove(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException;
}
