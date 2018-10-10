package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;
import nc.vo.pub.BusinessException;

public interface IHql_familyfileMaintain {

	public void delete(AggFamilyfileVO[] vos) throws BusinessException;

	public AggFamilyfileVO[] insert(AggFamilyfileVO[] vos) throws BusinessException;

	public AggFamilyfileVO[] update(AggFamilyfileVO[] vos) throws BusinessException;

	public AggFamilyfileVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}