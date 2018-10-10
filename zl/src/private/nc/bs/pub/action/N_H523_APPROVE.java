package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_parkcontract.plugin.bpplugin.Ld_parkcontractPluginPoint;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.itf.zl.ILd_parkcontractMaintain;

public class N_H523_APPROVE extends AbstractPfAction<AggParkcontractVO> {

	public N_H523_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggParkcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggParkcontractVO> processor = new CompareAroundProcesser<AggParkcontractVO>(
				Ld_parkcontractPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggParkcontractVO[] processBP(Object userObj,
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills) {
		AggParkcontractVO[] bills = null;
		ILd_parkcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_parkcontractMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
