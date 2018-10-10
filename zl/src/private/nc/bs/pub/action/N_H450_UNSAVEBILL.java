package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;

public class N_H450_UNSAVEBILL extends AbstractPfAction<AggUpcontractVO> {

	@Override
	protected CompareAroundProcesser<AggUpcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggUpcontractVO> processor = new CompareAroundProcesser<AggUpcontractVO>(
				Ld_upcontractPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggUpcontractVO[] processBP(Object userObj,
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills) {
		ILd_upcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_upcontractMaintain.class);
		AggUpcontractVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
