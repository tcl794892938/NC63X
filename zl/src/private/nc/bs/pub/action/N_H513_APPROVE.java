package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.itf.zl.ILy_hflistMaintain;

public class N_H513_APPROVE extends AbstractPfAction<AggHflistVO> {

	public N_H513_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggHflistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggHflistVO> processor = new CompareAroundProcesser<AggHflistVO>(
				Ly_hflistPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggHflistVO[] processBP(Object userObj,
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills) {
		AggHflistVO[] bills = null;
		ILy_hflistMaintain operator = NCLocator.getInstance().lookup(
				ILy_hflistMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
