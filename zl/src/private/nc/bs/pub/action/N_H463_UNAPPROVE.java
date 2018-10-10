package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.itf.zl.IHql_throwaleaseMaintain;

public class N_H463_UNAPPROVE extends AbstractPfAction<AggThrowaleaseVO> {

	@Override
	protected CompareAroundProcesser<AggThrowaleaseVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggThrowaleaseVO> processor = new CompareAroundProcesser<AggThrowaleaseVO>(
				Hql_throwaleasePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggThrowaleaseVO[] processBP(Object userObj,
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggThrowaleaseVO[] bills = null;
		try {
			IHql_throwaleaseMaintain operator = NCLocator.getInstance()
					.lookup(IHql_throwaleaseMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
