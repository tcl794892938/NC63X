package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_recbill.plugin.bpplugin.Cwf_recbillPluginPoint;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.itf.zl.ICwf_recbillMaintain;

public class N_H620_SAVEBASE extends AbstractPfAction<AggRecbillVO> {

	@Override
	protected CompareAroundProcesser<AggRecbillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggRecbillVO> processor = null;
		AggRecbillVO[] clientFullVOs = (AggRecbillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggRecbillVO>(
					Cwf_recbillPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggRecbillVO>(
					Cwf_recbillPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggRecbillVO> rule = null;

		return processor;
	}

	@Override
	protected AggRecbillVO[] processBP(Object userObj,
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills) {

		AggRecbillVO[] bills = null;
		try {
			ICwf_recbillMaintain operator = NCLocator.getInstance()
					.lookup(ICwf_recbillMaintain.class);
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
