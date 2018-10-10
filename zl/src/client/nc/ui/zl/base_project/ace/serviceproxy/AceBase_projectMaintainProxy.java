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
 * ʾ�����ݵĲ�������
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
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
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
