package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_prepayment.plugin.bpplugin.Hql_prepaymentPluginPoint;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.itf.zl.IHql_prepaymentMaintain;

public class N_H531_APPROVE extends AbstractPfAction<AggPrepaymentVO> {

	public N_H531_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggPrepaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPrepaymentVO> processor = new CompareAroundProcesser<AggPrepaymentVO>(
				Hql_prepaymentPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggPrepaymentVO[] processBP(Object userObj,
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills) {
		AggPrepaymentVO[] bills = null;
		IHql_prepaymentMaintain operator = NCLocator.getInstance().lookup(
				IHql_prepaymentMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
