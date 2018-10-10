package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_parkcontract.plugin.bpplugin.Ld_parkcontractPluginPoint;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.itf.zl.ILd_parkcontractMaintain;

public class N_H523_SAVEBASE extends AbstractPfAction<AggParkcontractVO> {

	@Override
	protected CompareAroundProcesser<AggParkcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggParkcontractVO> processor = null;
		AggParkcontractVO[] clientFullVOs = (AggParkcontractVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggParkcontractVO>(
					Ld_parkcontractPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggParkcontractVO>(
					Ld_parkcontractPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggParkcontractVO> rule = null;

		return processor;
	}

	@Override
	protected AggParkcontractVO[] processBP(Object userObj,
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills) {

		AggParkcontractVO[] bills = null;
		try {
			ILd_parkcontractMaintain operator = NCLocator.getInstance()
					.lookup(ILd_parkcontractMaintain.class);
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
