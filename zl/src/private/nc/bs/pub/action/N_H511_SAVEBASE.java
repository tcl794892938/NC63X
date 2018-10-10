package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_bslist.plugin.bpplugin.Ly_bslistPluginPoint;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.itf.zl.ILy_bslistMaintain;

public class N_H511_SAVEBASE extends AbstractPfAction<AggBslistVO> {

	@Override
	protected CompareAroundProcesser<AggBslistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBslistVO> processor = null;
		AggBslistVO[] clientFullVOs = (AggBslistVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggBslistVO>(
					Ly_bslistPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggBslistVO>(
					Ly_bslistPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggBslistVO> rule = null;

		return processor;
	}

	@Override
	protected AggBslistVO[] processBP(Object userObj,
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills) {

		AggBslistVO[] bills = null;
		try {
			ILy_bslistMaintain operator = NCLocator.getInstance()
					.lookup(ILy_bslistMaintain.class);
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
