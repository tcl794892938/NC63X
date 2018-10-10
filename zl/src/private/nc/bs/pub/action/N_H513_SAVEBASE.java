package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.itf.zl.ILy_hflistMaintain;

public class N_H513_SAVEBASE extends AbstractPfAction<AggHflistVO> {

	@Override
	protected CompareAroundProcesser<AggHflistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggHflistVO> processor = null;
		AggHflistVO[] clientFullVOs = (AggHflistVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggHflistVO>(
					Ly_hflistPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggHflistVO>(
					Ly_hflistPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggHflistVO> rule = null;

		return processor;
	}

	@Override
	protected AggHflistVO[] processBP(Object userObj,
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills) {

		AggHflistVO[] bills = null;
		try {
			ILy_hflistMaintain operator = NCLocator.getInstance()
					.lookup(ILy_hflistMaintain.class);
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
