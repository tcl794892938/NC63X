package nc.ui.zl.cwf_sales.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_sales.AggSalesVO;
import nc.itf.zl.ICwf_salesMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceCwf_salesMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggSalesVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ICwf_salesMaintain operator = NCLocator.getInstance().lookup(
				ICwf_salesMaintain.class);
		AggSalesVO[] vos = operator.insert((AggSalesVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ICwf_salesMaintain operator = NCLocator.getInstance().lookup(
				ICwf_salesMaintain.class);
		AggSalesVO[] vos = operator.update((AggSalesVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		ICwf_salesMaintain operator = NCLocator.getInstance().lookup(
				ICwf_salesMaintain.class);
		operator.delete((AggSalesVO[]) value);
		return value;
	}
	
	@Override
	public AggSalesVO operateBill(AggSalesVO bill) throws Exception {
		ICwf_salesMaintain operator = NCLocator.getInstance().lookup(
				ICwf_salesMaintain.class);
		operator.delete(new AggSalesVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ICwf_salesMaintain query = NCLocator.getInstance().lookup(
				ICwf_salesMaintain.class);
		return query.query(queryScheme);
	}

}
