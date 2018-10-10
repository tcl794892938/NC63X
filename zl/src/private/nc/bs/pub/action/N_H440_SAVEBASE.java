package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_mdcontract.plugin.bpplugin.Ld_mdcontractPluginPoint;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.itf.zl.ILd_mdcontractMaintain;

public class N_H440_SAVEBASE extends AbstractPfAction<AggMdcontractVO> {

	@Override
	protected CompareAroundProcesser<AggMdcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggMdcontractVO> processor = null;
		AggMdcontractVO[] clientFullVOs = (AggMdcontractVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggMdcontractVO>(
					Ld_mdcontractPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggMdcontractVO>(
					Ld_mdcontractPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggMdcontractVO> rule = null;

		return processor;
	}

	@Override
	protected AggMdcontractVO[] processBP(Object userObj,
			AggMdcontractVO[] clientFullVOs, AggMdcontractVO[] originBills) {

		AggMdcontractVO[] bills = null;
		try {
			ILd_mdcontractMaintain operator = NCLocator.getInstance()
					.lookup(ILd_mdcontractMaintain.class);
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
