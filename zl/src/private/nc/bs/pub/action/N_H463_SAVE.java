package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.itf.zl.IHql_throwaleaseMaintain;

public class N_H463_SAVE extends AbstractPfAction<AggThrowaleaseVO> {

	protected CompareAroundProcesser<AggThrowaleaseVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggThrowaleaseVO> processor = new CompareAroundProcesser<AggThrowaleaseVO>(
				Hql_throwaleasePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggThrowaleaseVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggThrowaleaseVO[] processBP(Object userObj,
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills) {
		IHql_throwaleaseMaintain operator = NCLocator.getInstance().lookup(
				IHql_throwaleaseMaintain.class);
		AggThrowaleaseVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
