package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_prepayment.plugin.bpplugin.Hql_prepaymentPluginPoint;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.itf.zl.IHql_prepaymentMaintain;

public class N_H531_UNAPPROVE extends AbstractPfAction<AggPrepaymentVO> {

	@Override
	protected CompareAroundProcesser<AggPrepaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPrepaymentVO> processor = new CompareAroundProcesser<AggPrepaymentVO>(
				Hql_prepaymentPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggPrepaymentVO[] processBP(Object userObj,
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggPrepaymentVO[] bills = null;
		try {
			IHql_prepaymentMaintain operator = NCLocator.getInstance()
					.lookup(IHql_prepaymentMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
