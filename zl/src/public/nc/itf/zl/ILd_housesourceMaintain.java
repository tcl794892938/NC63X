package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.pub.BusinessException;

public interface ILd_housesourceMaintain {

	public void delete(AggHousesourceVO[] vos) throws BusinessException;

	public AggHousesourceVO[] insert(AggHousesourceVO[] vos) throws BusinessException;

	public AggHousesourceVO[] update(AggHousesourceVO[] vos) throws BusinessException;

	public AggHousesourceVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}