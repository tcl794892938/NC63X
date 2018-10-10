package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_bslist.plugin.bpplugin.Ly_bslistPluginPoint;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.itf.zl.ILy_bslistMaintain;

public class N_H511_SAVE extends AbstractPfAction<AggBslistVO> {

	protected CompareAroundProcesser<AggBslistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBslistVO> processor = new CompareAroundProcesser<AggBslistVO>(
				Ly_bslistPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggBslistVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggBslistVO[] processBP(Object userObj,
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills) {
		ILy_bslistMaintain operator = NCLocator.getInstance().lookup(
				ILy_bslistMaintain.class);
		AggBslistVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
