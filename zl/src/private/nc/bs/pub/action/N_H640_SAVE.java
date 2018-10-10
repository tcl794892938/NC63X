package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.lyw_confirmation.plugin.bpplugin.Lyw_confirmationPluginPoint;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.itf.zl.ILyw_confirmationMaintain;

public class N_H640_SAVE extends AbstractPfAction<AggConfirmationVO> {

	protected CompareAroundProcesser<AggConfirmationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggConfirmationVO> processor = new CompareAroundProcesser<AggConfirmationVO>(
				Lyw_confirmationPluginPoint.SEND_APPROVE);
		// TODO �ڴ˴�������ǰ�����
		IRule<AggConfirmationVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggConfirmationVO[] processBP(Object userObj,
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills) {
		ILyw_confirmationMaintain operator = NCLocator.getInstance().lookup(
				ILyw_confirmationMaintain.class);
		AggConfirmationVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
