package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_entryacceptance.plugin.bpplugin.Hql_entryacceptancePluginPoint;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.itf.zl.IHql_entryacceptanceMaintain;

public class N_H462_DELETE extends AbstractPfAction<AggEntryacceptanceVO> {

	@Override
	protected CompareAroundProcesser<AggEntryacceptanceVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggEntryacceptanceVO> processor = new CompareAroundProcesser<AggEntryacceptanceVO>(
				Hql_entryacceptancePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggEntryacceptanceVO[] processBP(Object userObj,
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills) {
		IHql_entryacceptanceMaintain operator = NCLocator.getInstance().lookup(
				IHql_entryacceptanceMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
