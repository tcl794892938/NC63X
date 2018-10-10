package nc.ui.zl.base_project.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.base_project.AggProjectVO;
import nc.itf.zl.IBase_projectMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBase_projectMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggProjectVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IBase_projectMaintain operator = NCLocator.getInstance().lookup(
				IBase_projectMaintain.class);
		AggProjectVO[] vos = operator.insert((AggProjectVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IBase_projectMaintain operator = NCLocator.getInstance().lookup(
				IBase_projectMaintain.class);
		AggProjectVO[] vos = operator.update((AggProjectVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		IBase_projectMaintain operator = NCLocator.getInstance().lookup(
				IBase_projectMaintain.class);
		operator.delete((AggProjectVO[]) value);
		return value;
	}
	
	@Override
	public AggProjectVO operateBill(AggProjectVO bill) throws Exception {
		IBase_projectMaintain operator = NCLocator.getInstance().lookup(
				IBase_projectMaintain.class);
		operator.delete(new AggProjectVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBase_projectMaintain query = NCLocator.getInstance().lookup(
				IBase_projectMaintain.class);
		return query.query(queryScheme);
	}

}
