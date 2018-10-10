package nc.ui.zl.hql_familyfile.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;
import nc.itf.zl.IHql_familyfileMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_familyfileMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggFamilyfileVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IHql_familyfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_familyfileMaintain.class);
		AggFamilyfileVO[] vos = operator.insert((AggFamilyfileVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IHql_familyfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_familyfileMaintain.class);
		AggFamilyfileVO[] vos = operator.update((AggFamilyfileVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		IHql_familyfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_familyfileMaintain.class);
		operator.delete((AggFamilyfileVO[]) value);
		return value;
	}
	
	@Override
	public AggFamilyfileVO operateBill(AggFamilyfileVO bill) throws Exception {
		IHql_familyfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_familyfileMaintain.class);
		operator.delete(new AggFamilyfileVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_familyfileMaintain query = NCLocator.getInstance().lookup(
				IHql_familyfileMaintain.class);
		return query.query(queryScheme);
	}

}
