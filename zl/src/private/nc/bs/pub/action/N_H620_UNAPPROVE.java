package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_recbill.plugin.bpplugin.Cwf_recbillPluginPoint;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.itf.zl.ICwf_recbillMaintain;

public class N_H620_UNAPPROVE extends AbstractPfAction<AggRecbillVO> {

	@Override
	protected CompareAroundProcesser<AggRecbillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggRecbillVO> processor = new CompareAroundProcesser<AggRecbillVO>(
				Cwf_recbillPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggRecbillVO[] processBP(Object userObj,
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggRecbillVO[] bills = null;
		try {
			ICwf_recbillMaintain operator = NCLocator.getInstance()
					.lookup(ICwf_recbillMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
