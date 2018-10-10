package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;

public class N_H450_UNAPPROVE extends AbstractPfAction<AggUpcontractVO> {

	@Override
	protected CompareAroundProcesser<AggUpcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggUpcontractVO> processor = new CompareAroundProcesser<AggUpcontractVO>(
				Ld_upcontractPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggUpcontractVO[] processBP(Object userObj,
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggUpcontractVO[] bills = null;
		try {
			ILd_upcontractMaintain operator = NCLocator.getInstance()
					.lookup(ILd_upcontractMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
