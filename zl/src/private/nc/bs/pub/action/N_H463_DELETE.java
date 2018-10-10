package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.itf.zl.IHql_throwaleaseMaintain;

public class N_H463_DELETE extends AbstractPfAction<AggThrowaleaseVO> {

	@Override
	protected CompareAroundProcesser<AggThrowaleaseVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggThrowaleaseVO> processor = new CompareAroundProcesser<AggThrowaleaseVO>(
				Hql_throwaleasePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggThrowaleaseVO[] processBP(Object userObj,
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills) {
		IHql_throwaleaseMaintain operator = NCLocator.getInstance().lookup(
				IHql_throwaleaseMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
