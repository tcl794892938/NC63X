package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_sgmoney.plugin.bpplugin.Ly_sgmoneyPluginPoint;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.itf.zl.ILy_sgmoneyMaintain;

public class N_H610_SAVEBASE extends AbstractPfAction<AggSgMoneyVO> {

	@Override
	protected CompareAroundProcesser<AggSgMoneyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggSgMoneyVO> processor = null;
		AggSgMoneyVO[] clientFullVOs = (AggSgMoneyVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggSgMoneyVO>(
					Ly_sgmoneyPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggSgMoneyVO>(
					Ly_sgmoneyPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggSgMoneyVO> rule = null;

		return processor;
	}

	@Override
	protected AggSgMoneyVO[] processBP(Object userObj,
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills) {

		AggSgMoneyVO[] bills = null;
		try {
			ILy_sgmoneyMaintain operator = NCLocator.getInstance()
					.lookup(ILy_sgmoneyMaintain.class);
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
