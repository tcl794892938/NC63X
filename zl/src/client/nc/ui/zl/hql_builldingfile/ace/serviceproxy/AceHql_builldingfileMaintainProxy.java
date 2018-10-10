package nc.ui.zl.hql_builldingfile.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;
import nc.itf.zl.IHql_builldingfileMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHql_builldingfileMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggBuildingfileVO>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		IHql_builldingfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_builldingfileMaintain.class);
		AggBuildingfileVO[] vos = operator.insert((AggBuildingfileVO[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		IHql_builldingfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_builldingfileMaintain.class);
		AggBuildingfileVO[] vos = operator.update((AggBuildingfileVO[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		IHql_builldingfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_builldingfileMaintain.class);
		operator.delete((AggBuildingfileVO[]) value);
		return value;
	}
	
	@Override
	public AggBuildingfileVO operateBill(AggBuildingfileVO bill) throws Exception {
		IHql_builldingfileMaintain operator = NCLocator.getInstance().lookup(
				IHql_builldingfileMaintain.class);
		operator.delete(new AggBuildingfileVO[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHql_builldingfileMaintain query = NCLocator.getInstance().lookup(
				IHql_builldingfileMaintain.class);
		return query.query(queryScheme);
	}

}
