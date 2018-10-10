package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.lyw_confirmation.plugin.bpplugin.Lyw_confirmationPluginPoint;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.itf.zl.ILyw_confirmationMaintain;

public class N_H640_UNAPPROVE extends AbstractPfAction<AggConfirmationVO> {

	@Override
	protected CompareAroundProcesser<AggConfirmationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggConfirmationVO> processor = new CompareAroundProcesser<AggConfirmationVO>(
				Lyw_confirmationPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggConfirmationVO[] processBP(Object userObj,
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggConfirmationVO[] bills = null;
		try {
			ILyw_confirmationMaintain operator = NCLocator.getInstance()
					.lookup(ILyw_confirmationMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
