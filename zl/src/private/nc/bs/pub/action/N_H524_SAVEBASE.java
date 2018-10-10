package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_carcontract.plugin.bpplugin.Ld_carcontractPluginPoint;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.itf.zl.ILd_carcontractMaintain;

public class N_H524_SAVEBASE extends AbstractPfAction<AggCarcontractVO> {

	@Override
	protected CompareAroundProcesser<AggCarcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarcontractVO> processor = null;
		AggCarcontractVO[] clientFullVOs = (AggCarcontractVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggCarcontractVO>(
					Ld_carcontractPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggCarcontractVO>(
					Ld_carcontractPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggCarcontractVO> rule = null;

		return processor;
	}

	@Override
	protected AggCarcontractVO[] processBP(Object userObj,
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills) {

		AggCarcontractVO[] bills = null;
		try {
			ILd_carcontractMaintain operator = NCLocator.getInstance()
					.lookup(ILd_carcontractMaintain.class);
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
