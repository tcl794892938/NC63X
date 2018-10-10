package nc.ui.zl.lm_customer.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.lm_customer.AggCustomerVO;
import nc.itf.zl.ILm_customerMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLm_customerMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggCustomerVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ILm_customerMaintain operator = NCLocator.getInstance().lookup(
				ILm_customerMaintain.class);
		AggCustomerVO[] vos = operator.insert((AggCustomerVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ILm_customerMaintain operator = NCLocator.getInstance().lookup(
				ILm_customerMaintain.class);
		AggCustomerVO[] vos = operator.update((AggCustomerVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		ILm_customerMaintain operator = NCLocator.getInstance().lookup(
				ILm_customerMaintain.class);
		operator.delete((AggCustomerVO[]) value);
		return value;
	}
	
	@Override
	public AggCustomerVO operateBill(AggCustomerVO bill) throws Exception {
		ILm_customerMaintain operator = NCLocator.getInstance().lookup(
				ILm_customerMaintain.class);
		operator.delete(new AggCustomerVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILm_customerMaintain query = NCLocator.getInstance().lookup(
				ILm_customerMaintain.class);
		return query.query(queryScheme);
	}

}
