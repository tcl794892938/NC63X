package nc.ui.zl.ly_businesssource.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;
import nc.itf.zl.ILy_businesssourceMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceLy_businesssourceMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggBusinessSourceVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ILy_businesssourceMaintain operator = NCLocator.getInstance().lookup(
				ILy_businesssourceMaintain.class);
		AggBusinessSourceVO[] vos = operator.insert((AggBusinessSourceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ILy_businesssourceMaintain operator = NCLocator.getInstance().lookup(
				ILy_businesssourceMaintain.class);
		AggBusinessSourceVO[] vos = operator.update((AggBusinessSourceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		ILy_businesssourceMaintain operator = NCLocator.getInstance().lookup(
				ILy_businesssourceMaintain.class);
		operator.delete((AggBusinessSourceVO[]) value);
		return value;
	}
	
	@Override
	public AggBusinessSourceVO operateBill(AggBusinessSourceVO bill) throws Exception {
		ILy_businesssourceMaintain operator = NCLocator.getInstance().lookup(
				ILy_businesssourceMaintain.class);
		operator.delete(new AggBusinessSourceVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ILy_businesssourceMaintain query = NCLocator.getInstance().lookup(
				ILy_businesssourceMaintain.class);
		return query.query(queryScheme);
	}

}
