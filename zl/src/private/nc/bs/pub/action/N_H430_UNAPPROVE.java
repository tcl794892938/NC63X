package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.itf.zl.ITcl_contractMaintain;

public class N_H430_UNAPPROVE extends AbstractPfAction<AggContractVO> {

	@Override
	protected CompareAroundProcesser<AggContractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggContractVO> processor = new CompareAroundProcesser<AggContractVO>(
				Tcl_contractPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggContractVO[] processBP(Object userObj,
			AggContractVO[] clientFullVOs, AggContractVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggContractVO[] bills = null;
		try {
			ITcl_contractMaintain operator = NCLocator.getInstance()
					.lookup(ITcl_contractMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
