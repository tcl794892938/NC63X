package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.BusinessException;

public interface ICwf_carconeditMaintain {

	public void delete(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] insert(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] update(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggCarconeditVO[] save(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] unsave(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] approve(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;

	public AggCarconeditVO[] unapprove(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException;
}
