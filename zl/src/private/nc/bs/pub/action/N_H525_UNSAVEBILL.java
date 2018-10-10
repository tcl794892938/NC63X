package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.itf.zl.ICwf_carconeditMaintain;

public class N_H525_UNSAVEBILL extends AbstractPfAction<AggCarconeditVO> {

	@Override
	protected CompareAroundProcesser<AggCarconeditVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarconeditVO> processor = new CompareAroundProcesser<AggCarconeditVO>(
				Cwf_carconeditPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggCarconeditVO[] processBP(Object userObj,
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills) {
		ICwf_carconeditMaintain operator = NCLocator.getInstance().lookup(
				ICwf_carconeditMaintain.class);
		AggCarconeditVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
