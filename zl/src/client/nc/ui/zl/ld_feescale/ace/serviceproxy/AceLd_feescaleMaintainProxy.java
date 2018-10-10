package nc.ui.zl.ld_feescale.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_feescale.AggFeescaleVO;
import nc.itf.zl.ILd_feescaleMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceLd_feescaleMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggFeescaleVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ILd_feescaleMaintain operator = NCLocator.getInstance().lookup(
				ILd_feescaleMaintain.class);
		AggFeescaleVO[] vos = operator.insert((AggFeescaleVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ILd_feescaleMaintain operator = NCLocator.getInstance().lookup(
				ILd_feescaleMaintain.class);
		AggFeescaleVO[] vos = operator.update((AggFeescaleVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		ILd_feescaleMaintain operator = NCLocator.getInstance().lookup(
				ILd_feescaleMaintain.class);
		operator.delete((AggFeescaleVO[]) value);
		return value;
	}
	
	@Override
	public AggFeescaleVO operateBill(AggFeescaleVO bill) throws Exception {
		ILd_feescaleMaintain operator = NCLocator.getInstance().lookup(
				ILd_feescaleMaintain.class);
		operator.delete(new AggFeescaleVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILd_feescaleMaintain query = NCLocator.getInstance().lookup(
				ILd_feescaleMaintain.class);
		return query.query(queryScheme);
	}

}
