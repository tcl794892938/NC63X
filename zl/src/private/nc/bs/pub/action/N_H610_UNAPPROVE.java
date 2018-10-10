package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_sgmoney.plugin.bpplugin.Ly_sgmoneyPluginPoint;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.itf.zl.ILy_sgmoneyMaintain;

public class N_H610_UNAPPROVE extends AbstractPfAction<AggSgMoneyVO> {

	@Override
	protected CompareAroundProcesser<AggSgMoneyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSgMoneyVO> processor = new CompareAroundProcesser<AggSgMoneyVO>(
				Ly_sgmoneyPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggSgMoneyVO[] processBP(Object userObj,
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggSgMoneyVO[] bills = null;
		try {
			ILy_sgmoneyMaintain operator = NCLocator.getInstance()
					.lookup(ILy_sgmoneyMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
