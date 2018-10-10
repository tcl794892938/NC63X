package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.itf.zl.ITcl_contractMaintain;

public class N_H420_DELETE extends AbstractPfAction<AggContractVO> {

	@Override
	protected CompareAroundProcesser<AggContractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggContractVO> processor = new CompareAroundProcesser<AggContractVO>(
				Tcl_contractPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggContractVO[] processBP(Object userObj,
			AggContractVO[] clientFullVOs, AggContractVO[] originBills) {
		ITcl_contractMaintain operator = NCLocator.getInstance().lookup(
				ITcl_contractMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
