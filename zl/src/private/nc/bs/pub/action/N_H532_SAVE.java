package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_payment.plugin.bpplugin.Hql_paymentPluginPoint;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.itf.zl.IHql_paymentMaintain;

public class N_H532_SAVE extends AbstractPfAction<AggPaymentVO> {

	protected CompareAroundProcesser<AggPaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPaymentVO> processor = new CompareAroundProcesser<AggPaymentVO>(
				Hql_paymentPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggPaymentVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggPaymentVO[] processBP(Object userObj,
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills) {
		IHql_paymentMaintain operator = NCLocator.getInstance().lookup(
				IHql_paymentMaintain.class);
		AggPaymentVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
