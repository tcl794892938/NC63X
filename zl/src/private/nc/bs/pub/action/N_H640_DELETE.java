package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.lyw_confirmation.plugin.bpplugin.Lyw_confirmationPluginPoint;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.itf.zl.ILyw_confirmationMaintain;

public class N_H640_DELETE extends AbstractPfAction<AggConfirmationVO> {

	@Override
	protected CompareAroundProcesser<AggConfirmationVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggConfirmationVO> processor = new CompareAroundProcesser<AggConfirmationVO>(
				Lyw_confirmationPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggConfirmationVO[] processBP(Object userObj,
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills) {
		ILyw_confirmationMaintain operator = NCLocator.getInstance().lookup(
				ILyw_confirmationMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
