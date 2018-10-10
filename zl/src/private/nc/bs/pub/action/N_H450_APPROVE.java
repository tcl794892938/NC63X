package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;

public class N_H450_APPROVE extends AbstractPfAction<AggUpcontractVO> {

	public N_H450_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggUpcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggUpcontractVO> processor = new CompareAroundProcesser<AggUpcontractVO>(
				Ld_upcontractPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggUpcontractVO[] processBP(Object userObj,
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills) {
		AggUpcontractVO[] bills = null;
		ILd_upcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_upcontractMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
