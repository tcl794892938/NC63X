package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_kpregister.plugin.bpplugin.Ld_kpregisterPluginPoint;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.itf.zl.ILd_kpregisterMaintain;

public class N_H650_SAVEBASE extends AbstractPfAction<AggKpregisterVO> {

	@Override
	protected CompareAroundProcesser<AggKpregisterVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggKpregisterVO> processor = null;
		AggKpregisterVO[] clientFullVOs = (AggKpregisterVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggKpregisterVO>(
					Ld_kpregisterPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggKpregisterVO>(
					Ld_kpregisterPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggKpregisterVO> rule = null;

		return processor;
	}

	@Override
	protected AggKpregisterVO[] processBP(Object userObj,
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills) {

		AggKpregisterVO[] bills = null;
		try {
			ILd_kpregisterMaintain operator = NCLocator.getInstance()
					.lookup(ILd_kpregisterMaintain.class);
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
