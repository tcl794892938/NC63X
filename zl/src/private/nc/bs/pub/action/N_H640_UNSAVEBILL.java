package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.lyw_confirmation.plugin.bpplugin.Lyw_confirmationPluginPoint;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.itf.zl.ILyw_confirmationMaintain;

public class N_H640_UNSAVEBILL extends AbstractPfAction<AggConfirmationVO> {

	@Override
	protected CompareAroundProcesser<AggConfirmationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggConfirmationVO> processor = new CompareAroundProcesser<AggConfirmationVO>(
				Lyw_confirmationPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggConfirmationVO[] processBP(Object userObj,
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills) {
		ILyw_confirmationMaintain operator = NCLocator.getInstance().lookup(
				ILyw_confirmationMaintain.class);
		AggConfirmationVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
