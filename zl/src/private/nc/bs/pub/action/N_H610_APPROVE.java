package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_sgmoney.plugin.bpplugin.Ly_sgmoneyPluginPoint;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.itf.zl.ILy_sgmoneyMaintain;

public class N_H610_APPROVE extends AbstractPfAction<AggSgMoneyVO> {

	public N_H610_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggSgMoneyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSgMoneyVO> processor = new CompareAroundProcesser<AggSgMoneyVO>(
				Ly_sgmoneyPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggSgMoneyVO[] processBP(Object userObj,
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills) {
		AggSgMoneyVO[] bills = null;
		ILy_sgmoneyMaintain operator = NCLocator.getInstance().lookup(
				ILy_sgmoneyMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
