package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.itf.zl.ITcl_contractMaintain;

public class N_H430_SAVEBASE extends AbstractPfAction<AggContractVO> {

	@Override
	protected CompareAroundProcesser<AggContractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggContractVO> processor = null;
		AggContractVO[] clientFullVOs = (AggContractVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggContractVO>(
					Tcl_contractPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggContractVO>(
					Tcl_contractPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggContractVO> rule = null;

		return processor;
	}

	@Override
	protected AggContractVO[] processBP(Object userObj,
			AggContractVO[] clientFullVOs, AggContractVO[] originBills) {

		AggContractVO[] bills = null;
		try {
			ITcl_contractMaintain operator = NCLocator.getInstance()
					.lookup(ITcl_contractMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
