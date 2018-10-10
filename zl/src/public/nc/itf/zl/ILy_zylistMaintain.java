package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.pub.BusinessException;

public interface ILy_zylistMaintain {

	public void delete(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] insert(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] update(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] query(IQueryScheme queryScheme)
			throws BusinessException;
	
	public AggZylistVO[] query2(IQueryScheme queryScheme)
			throws BusinessException;

	public AggZylistVO[] save(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] unsave(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] approve(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;

	public AggZylistVO[] unapprove(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException;
}
