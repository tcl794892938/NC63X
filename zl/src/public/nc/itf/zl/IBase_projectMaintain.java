package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.base_project.AggProjectVO;
import nc.vo.pub.BusinessException;

public interface IBase_projectMaintain {

	public void delete(AggProjectVO[] vos) throws BusinessException;

	public AggProjectVO[] insert(AggProjectVO[] vos) throws BusinessException;

	public AggProjectVO[] update(AggProjectVO[] vos) throws BusinessException;

	public AggProjectVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}