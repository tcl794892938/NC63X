package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_entryacceptance.plugin.bpplugin.Hql_entryacceptancePluginPoint;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.itf.zl.IHql_entryacceptanceMaintain;

public class N_H462_SAVEBASE extends AbstractPfAction<AggEntryacceptanceVO> {

	@Override
	protected CompareAroundProcesser<AggEntryacceptanceVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggEntryacceptanceVO> processor = null;
		AggEntryacceptanceVO[] clientFullVOs = (AggEntryacceptanceVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggEntryacceptanceVO>(
					Hql_entryacceptancePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggEntryacceptanceVO>(
					Hql_entryacceptancePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggEntryacceptanceVO> rule = null;

		return processor;
	}

	@Override
	protected AggEntryacceptanceVO[] processBP(Object userObj,
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills) {

		AggEntryacceptanceVO[] bills = null;
		try {
			IHql_entryacceptanceMaintain operator = NCLocator.getInstance()
					.lookup(IHql_entryacceptanceMaintain.class);
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
