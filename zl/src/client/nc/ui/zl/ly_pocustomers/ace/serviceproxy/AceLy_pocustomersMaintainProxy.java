package nc.ui.zl.ly_pocustomers.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;
import nc.itf.zl.ILy_pocustomersMaintain;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_pocustomersMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggPocustomersVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ILy_pocustomersMaintain operator = NCLocator.getInstance().lookup(
				ILy_pocustomersMaintain.class);
		AggPocustomersVO[] vos = operator.insert((AggPocustomersVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ILy_pocustomersMaintain operator = NCLocator.getInstance().lookup(
				ILy_pocustomersMaintain.class);
		AggPocustomersVO[] vos = operator.update((AggPocustomersVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// 目前的删除并不是走这个方法，由于pubapp不支持从这个服务中执行删除操作
		// 单据的删除实际上使用的是：ISingleBillService<AggSingleBill>的operateBill
		ILy_pocustomersMaintain operator = NCLocator.getInstance().lookup(
				ILy_pocustomersMaintain.class);
		operator.delete((AggPocustomersVO[]) value);
		return value;
	}
	
	@Override
	public AggPocustomersVO operateBill(AggPocustomersVO bill) throws Exception {
		ILy_pocustomersMaintain operator = NCLocator.getInstance().lookup(
				ILy_pocustomersMaintain.class);
		operator.delete(new AggPocustomersVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_pocustomersMaintain query = NCLocator.getInstance().lookup(
				ILy_pocustomersMaintain.class);
		return query.query(queryScheme);
	}

}
