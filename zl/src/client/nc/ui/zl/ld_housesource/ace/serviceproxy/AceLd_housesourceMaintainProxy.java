package nc.ui.zl.ld_housesource.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.itf.zl.ILd_housesourceMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_housesourceMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggHousesourceVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ILd_housesourceMaintain operator = NCLocator.getInstance().lookup(
				ILd_housesourceMaintain.class);
		AggHousesourceVO[] vos = operator.insert((AggHousesourceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ILd_housesourceMaintain operator = NCLocator.getInstance().lookup(
				ILd_housesourceMaintain.class);
		AggHousesourceVO[] vos = operator.update((AggHousesourceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		ILd_housesourceMaintain operator = NCLocator.getInstance().lookup(
				ILd_housesourceMaintain.class);
		operator.delete((AggHousesourceVO[]) value);
		return value;
	}
	
	@Override
	public AggHousesourceVO operateBill(AggHousesourceVO bill) throws Exception {
		ILd_housesourceMaintain operator = NCLocator.getInstance().lookup(
				ILd_housesourceMaintain.class);
		operator.delete(new AggHousesourceVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILd_housesourceMaintain query = NCLocator.getInstance().lookup(
				ILd_housesourceMaintain.class);
		return query.query(queryScheme);
	}

}
