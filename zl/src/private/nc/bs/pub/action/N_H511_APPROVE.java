package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_bslist.plugin.bpplugin.Ly_bslistPluginPoint;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.itf.zl.ILy_bslistMaintain;

public class N_H511_APPROVE extends AbstractPfAction<AggBslistVO> {

	public N_H511_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggBslistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBslistVO> processor = new CompareAroundProcesser<AggBslistVO>(
				Ly_bslistPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggBslistVO[] processBP(Object userObj,
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills) {
		AggBslistVO[] bills = null;
		ILy_bslistMaintain operator = NCLocator.getInstance().lookup(
				ILy_bslistMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
