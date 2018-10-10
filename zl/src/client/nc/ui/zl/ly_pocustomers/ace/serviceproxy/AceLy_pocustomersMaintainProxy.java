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
 * ʾ�����ݵĲ�������
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
		// Ŀǰ��ɾ�����������������������pubapp��֧�ִ����������ִ��ɾ������
		// ���ݵ�ɾ��ʵ����ʹ�õ��ǣ�ISingleBillService<AggSingleBill>��operateBill
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
