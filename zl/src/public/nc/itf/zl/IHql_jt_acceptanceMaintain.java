package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;
import nc.vo.pub.BusinessException;

public interface IHql_jt_acceptanceMaintain {

	public void delete(AggJt_acceptanceVO[] vos) throws BusinessException;

	public AggJt_acceptanceVO[] insert(AggJt_acceptanceVO[] vos) throws BusinessException;

	public AggJt_acceptanceVO[] update(AggJt_acceptanceVO[] vos) throws BusinessException;

	public AggJt_acceptanceVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}