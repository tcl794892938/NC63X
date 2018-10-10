package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_sales.AggSalesVO;
import nc.vo.pub.BusinessException;

public interface ICwf_salesMaintain {

	public void delete(AggSalesVO[] vos) throws BusinessException;

	public AggSalesVO[] insert(AggSalesVO[] vos) throws BusinessException;

	public AggSalesVO[] update(AggSalesVO[] vos) throws BusinessException;

	public AggSalesVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}