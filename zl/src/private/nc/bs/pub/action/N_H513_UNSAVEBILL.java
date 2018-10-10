package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.itf.zl.ILy_hflistMaintain;

public class N_H513_UNSAVEBILL extends AbstractPfAction<AggHflistVO> {

	@Override
	protected CompareAroundProcesser<AggHflistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggHflistVO> processor = new CompareAroundProcesser<AggHflistVO>(
				Ly_hflistPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggHflistVO[] processBP(Object userObj,
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills) {
		ILy_hflistMaintain operator = NCLocator.getInstance().lookup(
				ILy_hflistMaintain.class);
		AggHflistVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
