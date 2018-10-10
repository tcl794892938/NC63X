package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_carcontract.plugin.bpplugin.Ld_carcontractPluginPoint;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.itf.zl.ILd_carcontractMaintain;

public class N_H524_DELETE extends AbstractPfAction<AggCarcontractVO> {

	@Override
	protected CompareAroundProcesser<AggCarcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarcontractVO> processor = new CompareAroundProcesser<AggCarcontractVO>(
				Ld_carcontractPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggCarcontractVO[] processBP(Object userObj,
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills) {
		ILd_carcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_carcontractMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
