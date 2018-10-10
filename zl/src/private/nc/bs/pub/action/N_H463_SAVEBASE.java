package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.itf.zl.IHql_throwaleaseMaintain;

public class N_H463_SAVEBASE extends AbstractPfAction<AggThrowaleaseVO> {

	@Override
	protected CompareAroundProcesser<AggThrowaleaseVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggThrowaleaseVO> processor = null;
		AggThrowaleaseVO[] clientFullVOs = (AggThrowaleaseVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggThrowaleaseVO>(
					Hql_throwaleasePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggThrowaleaseVO>(
					Hql_throwaleasePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggThrowaleaseVO> rule = null;

		return processor;
	}

	@Override
	protected AggThrowaleaseVO[] processBP(Object userObj,
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills) {

		AggThrowaleaseVO[] bills = null;
		try {
			IHql_throwaleaseMaintain operator = NCLocator.getInstance()
					.lookup(IHql_throwaleaseMaintain.class);
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
