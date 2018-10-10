package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_prepayment.plugin.bpplugin.Hql_prepaymentPluginPoint;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.itf.zl.IHql_prepaymentMaintain;

public class N_H531_DELETE extends AbstractPfAction<AggPrepaymentVO> {

	@Override
	protected CompareAroundProcesser<AggPrepaymentVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggPrepaymentVO> processor = new CompareAroundProcesser<AggPrepaymentVO>(
				Hql_prepaymentPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggPrepaymentVO[] processBP(Object userObj,
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills) {
		IHql_prepaymentMaintain operator = NCLocator.getInstance().lookup(
				IHql_prepaymentMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
