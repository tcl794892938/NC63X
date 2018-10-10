package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_parkcontract.plugin.bpplugin.Ld_parkcontractPluginPoint;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.itf.zl.ILd_parkcontractMaintain;

public class N_H523_UNAPPROVE extends AbstractPfAction<AggParkcontractVO> {

	@Override
	protected CompareAroundProcesser<AggParkcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggParkcontractVO> processor = new CompareAroundProcesser<AggParkcontractVO>(
				Ld_parkcontractPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggParkcontractVO[] processBP(Object userObj,
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggParkcontractVO[] bills = null;
		try {
			ILd_parkcontractMaintain operator = NCLocator.getInstance()
					.lookup(ILd_parkcontractMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
