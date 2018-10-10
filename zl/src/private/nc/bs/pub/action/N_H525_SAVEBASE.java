package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.itf.zl.ICwf_carconeditMaintain;

public class N_H525_SAVEBASE extends AbstractPfAction<AggCarconeditVO> {

	@Override
	protected CompareAroundProcesser<AggCarconeditVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarconeditVO> processor = null;
		AggCarconeditVO[] clientFullVOs = (AggCarconeditVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggCarconeditVO>(
					Cwf_carconeditPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggCarconeditVO>(
					Cwf_carconeditPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggCarconeditVO> rule = null;

		return processor;
	}

	@Override
	protected AggCarconeditVO[] processBP(Object userObj,
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills) {

		AggCarconeditVO[] bills = null;
		try {
			ICwf_carconeditMaintain operator = NCLocator.getInstance()
					.lookup(ICwf_carconeditMaintain.class);
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
