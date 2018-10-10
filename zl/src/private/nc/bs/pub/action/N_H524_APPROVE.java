package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_carcontract.plugin.bpplugin.Ld_carcontractPluginPoint;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.itf.zl.ILd_carcontractMaintain;

public class N_H524_APPROVE extends AbstractPfAction<AggCarcontractVO> {

	public N_H524_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggCarcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarcontractVO> processor = new CompareAroundProcesser<AggCarcontractVO>(
				Ld_carcontractPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggCarcontractVO[] processBP(Object userObj,
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills) {
		AggCarcontractVO[] bills = null;
		ILd_carcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_carcontractMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
