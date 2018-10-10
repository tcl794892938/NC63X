package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.itf.zl.ILd_upcontractMaintain;

public class N_H450_DELETE extends AbstractPfAction<AggUpcontractVO> {

	@Override
	protected CompareAroundProcesser<AggUpcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggUpcontractVO> processor = new CompareAroundProcesser<AggUpcontractVO>(
				Ld_upcontractPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggUpcontractVO[] processBP(Object userObj,
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills) {
		ILd_upcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_upcontractMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
