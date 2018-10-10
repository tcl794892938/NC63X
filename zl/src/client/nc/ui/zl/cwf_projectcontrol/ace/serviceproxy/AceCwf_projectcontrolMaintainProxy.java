package nc.ui.zl.cwf_projectcontrol.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.pubapp.uif2app.actions.IDataOperationService;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.uif2.components.pagination.IPaginationQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;
import nc.itf.zl.ICwf_projectcontrolMaintain;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceCwf_projectcontrolMaintainProxy implements IDataOperationService,
		IQueryService ,ISingleBillService<AggProjectcontrol>{
	@Override
	public IBill[] insert(IBill[] value) throws BusinessException {
		ICwf_projectcontrolMaintain operator = NCLocator.getInstance().lookup(
				ICwf_projectcontrolMaintain.class);
		AggProjectcontrol[] vos = operator.insert((AggProjectcontrol[]) value);
		return vos;
	}

	@Override
	public IBill[] update(IBill[] value) throws BusinessException {
		ICwf_projectcontrolMaintain operator = NCLocator.getInstance().lookup(
				ICwf_projectcontrolMaintain.class);
		AggProjectcontrol[] vos = operator.update((AggProjectcontrol[]) value);
		return vos;
	}

	@Override
	public IBill[] delete(IBill[] value) throws BusinessException {
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
		ICwf_projectcontrolMaintain operator = NCLocator.getInstance().lookup(
				ICwf_projectcontrolMaintain.class);
		operator.delete((AggProjectcontrol[]) value);
		return value;
	}
	
	@Override
	public AggProjectcontrol operateBill(AggProjectcontrol bill) throws Exception {
		ICwf_projectcontrolMaintain operator = NCLocator.getInstance().lookup(
				ICwf_projectcontrolMaintain.class);
		operator.delete(new AggProjectcontrol[] { bill });
		return bill;
	}

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		ICwf_projectcontrolMaintain query = NCLocator.getInstance().lookup(
				ICwf_projectcontrolMaintain.class);
		return query.query(queryScheme);
	}

}
