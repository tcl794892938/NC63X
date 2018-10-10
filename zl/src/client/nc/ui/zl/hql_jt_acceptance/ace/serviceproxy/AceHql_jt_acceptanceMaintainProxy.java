package nc.ui.zl.hql_jt_acceptance.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;
import nc.itf.zl.IHql_jt_acceptanceMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_jt_acceptanceMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggJt_acceptanceVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IHql_jt_acceptanceMaintain operator = NCLocator.getInstance().lookup(
				IHql_jt_acceptanceMaintain.class);
		AggJt_acceptanceVO[] vos = operator.insert((AggJt_acceptanceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IHql_jt_acceptanceMaintain operator = NCLocator.getInstance().lookup(
				IHql_jt_acceptanceMaintain.class);
		AggJt_acceptanceVO[] vos = operator.update((AggJt_acceptanceVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		IHql_jt_acceptanceMaintain operator = NCLocator.getInstance().lookup(
				IHql_jt_acceptanceMaintain.class);
		operator.delete((AggJt_acceptanceVO[]) value);
		return value;
	}
	
	@Override
	public AggJt_acceptanceVO operateBill(AggJt_acceptanceVO bill) throws Exception {
		IHql_jt_acceptanceMaintain operator = NCLocator.getInstance().lookup(
				IHql_jt_acceptanceMaintain.class);
		operator.delete(new AggJt_acceptanceVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_jt_acceptanceMaintain query = NCLocator.getInstance().lookup(
				IHql_jt_acceptanceMaintain.class);
		return query.query(queryScheme);
	}

}
