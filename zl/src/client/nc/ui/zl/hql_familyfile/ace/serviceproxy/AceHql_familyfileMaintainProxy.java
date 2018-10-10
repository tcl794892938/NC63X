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
 * ʾ�����ݵĲ�������
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
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
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
