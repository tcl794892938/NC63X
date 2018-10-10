package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.itf.zl.ICwf_carconeditMaintain;

public class N_H525_SAVE extends AbstractPfAction<AggCarconeditVO> {

	protected CompareAroundProcesser<AggCarconeditVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarconeditVO> processor = new CompareAroundProcesser<AggCarconeditVO>(
				Cwf_carconeditPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggCarconeditVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggCarconeditVO[] processBP(Object userObj,
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills) {
		ICwf_carconeditMaintain operator = NCLocator.getInstance().lookup(
				ICwf_carconeditMaintain.class);
		AggCarconeditVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
