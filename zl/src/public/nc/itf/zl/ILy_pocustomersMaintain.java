package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;
import nc.vo.pub.BusinessException;

public interface ILy_pocustomersMaintain {

	public void delete(AggPocustomersVO[] vos) throws BusinessException;

	public AggPocustomersVO[] insert(AggPocustomersVO[] vos) throws BusinessException;

	public AggPocustomersVO[] update(AggPocustomersVO[] vos) throws BusinessException;

	public AggPocustomersVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}