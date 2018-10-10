package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_feescale.AggFeescaleVO;
import nc.vo.pub.BusinessException;

public interface ILd_feescaleMaintain {

	public void delete(AggFeescaleVO[] vos) throws BusinessException;

	public AggFeescaleVO[] insert(AggFeescaleVO[] vos) throws BusinessException;

	public AggFeescaleVO[] update(AggFeescaleVO[] vos) throws BusinessException;

	public AggFeescaleVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}