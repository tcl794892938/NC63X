package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;
import nc.vo.pub.BusinessException;

public interface ILy_businesssourceMaintain {

	public void delete(AggBusinessSourceVO[] vos) throws BusinessException;

	public AggBusinessSourceVO[] insert(AggBusinessSourceVO[] vos) throws BusinessException;

	public AggBusinessSourceVO[] update(AggBusinessSourceVO[] vos) throws BusinessException;

	public AggBusinessSourceVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}