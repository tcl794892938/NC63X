package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_payment.plugin.bpplugin.Hql_paymentPluginPoint;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.itf.zl.IHql_paymentMaintain;

public class N_H532_APPROVE extends AbstractPfAction<AggPaymentVO> {

	public N_H532_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggPaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPaymentVO> processor = new CompareAroundProcesser<AggPaymentVO>(
				Hql_paymentPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggPaymentVO[] processBP(Object userObj,
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills) {
		AggPaymentVO[] bills = null;
		IHql_paymentMaintain operator = NCLocator.getInstance().lookup(
				IHql_paymentMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}