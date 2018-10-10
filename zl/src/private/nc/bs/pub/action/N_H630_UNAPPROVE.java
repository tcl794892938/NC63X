package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_gather.plugin.bpplugin.Cwf_gatherPluginPoint;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.itf.zl.ICwf_gatherMaintain;

public class N_H630_UNAPPROVE extends AbstractPfAction<AggGatherVO> {

	@Override
	protected CompareAroundProcesser<AggGatherVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggGatherVO> processor = new CompareAroundProcesser<AggGatherVO>(
				Cwf_gatherPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggGatherVO[] processBP(Object userObj,
			AggGatherVO[] clientFullVOs, AggGatherVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggGatherVO[] bills = null;
		try {
			ICwf_gatherMaintain operator = NCLocator.getInstance()
					.lookup(ICwf_gatherMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
