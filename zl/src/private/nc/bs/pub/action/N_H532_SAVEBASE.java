package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_payment.plugin.bpplugin.Hql_paymentPluginPoint;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.itf.zl.IHql_paymentMaintain;

public class N_H532_SAVEBASE extends AbstractPfAction<AggPaymentVO> {

	@Override
	protected CompareAroundProcesser<AggPaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPaymentVO> processor = null;
		AggPaymentVO[] clientFullVOs = (AggPaymentVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggPaymentVO>(
					Hql_paymentPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggPaymentVO>(
					Hql_paymentPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggPaymentVO> rule = null;

		return processor;
	}

	@Override
	protected AggPaymentVO[] processBP(Object userObj,
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills) {

		AggPaymentVO[] bills = null;
		try {
			IHql_paymentMaintain operator = NCLocator.getInstance()
					.lookup(IHql_paymentMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
