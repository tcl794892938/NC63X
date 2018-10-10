package nc.itf.zl;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.vo.pub.BusinessException;

public interface ILm_customerMaintain {

	public void delete(AggCustomerVO[] vos) throws BusinessException;

	public AggCustomerVO[] insert(AggCustomerVO[] vos) throws BusinessException;

	public AggCustomerVO[] update(AggCustomerVO[] vos) throws BusinessException;

	public AggCustomerVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

}