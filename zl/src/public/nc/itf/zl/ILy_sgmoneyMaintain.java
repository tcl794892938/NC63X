package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.BusinessException;

public interface ILy_sgmoneyMaintain {

	public void delete(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] insert(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] update(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggSgMoneyVO[] save(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] unsave(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] approve(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;

	public AggSgMoneyVO[] unapprove(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException;
}
