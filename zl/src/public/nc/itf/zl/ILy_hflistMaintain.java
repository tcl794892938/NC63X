package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.vo.pub.BusinessException;

public interface ILy_hflistMaintain {

	public void delete(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] insert(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] update(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggHflistVO[] save(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] unsave(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] approve(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;

	public AggHflistVO[] unapprove(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException;
}
