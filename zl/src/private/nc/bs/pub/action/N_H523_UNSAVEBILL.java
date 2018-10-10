package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_parkcontract.plugin.bpplugin.Ld_parkcontractPluginPoint;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.itf.zl.ILd_parkcontractMaintain;

public class N_H523_UNSAVEBILL extends AbstractPfAction<AggParkcontractVO> {

	@Override
	protected CompareAroundProcesser<AggParkcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggParkcontractVO> processor = new CompareAroundProcesser<AggParkcontractVO>(
				Ld_parkcontractPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggParkcontractVO[] processBP(Object userObj,
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills) {
		ILd_parkcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_parkcontractMaintain.class);
		AggParkcontractVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
