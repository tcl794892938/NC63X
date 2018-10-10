package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.itf.zl.ITcl_contractMaintain;

public class N_H430_APPROVE extends AbstractPfAction<AggContractVO> {

	public N_H430_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggContractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggContractVO> processor = new CompareAroundProcesser<AggContractVO>(
				Tcl_contractPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggContractVO[] processBP(Object userObj,
			AggContractVO[] clientFullVOs, AggContractVO[] originBills) {
		AggContractVO[] bills = null;
		ITcl_contractMaintain operator = NCLocator.getInstance().lookup(
				ITcl_contractMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
