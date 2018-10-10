package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_recbill.plugin.bpplugin.Cwf_recbillPluginPoint;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.itf.zl.ICwf_recbillMaintain;

public class N_H620_SAVE extends AbstractPfAction<AggRecbillVO> {

	protected CompareAroundProcesser<AggRecbillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggRecbillVO> processor = new CompareAroundProcesser<AggRecbillVO>(
				Cwf_recbillPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggRecbillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggRecbillVO[] processBP(Object userObj,
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills) {
		ICwf_recbillMaintain operator = NCLocator.getInstance().lookup(
				ICwf_recbillMaintain.class);
		AggRecbillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
