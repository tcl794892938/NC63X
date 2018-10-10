package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;

public class N_H450_SAVE extends AbstractPfAction<AggUpcontractVO> {

	protected CompareAroundProcesser<AggUpcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggUpcontractVO> processor = new CompareAroundProcesser<AggUpcontractVO>(
				Ld_upcontractPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggUpcontractVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggUpcontractVO[] processBP(Object userObj,
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills) {
		ILd_upcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_upcontractMaintain.class);
		AggUpcontractVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
