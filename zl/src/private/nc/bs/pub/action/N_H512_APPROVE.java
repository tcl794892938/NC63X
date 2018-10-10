package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_zylist.plugin.bpplugin.Ly_zylistPluginPoint;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.itf.zl.ILy_zylistMaintain;

public class N_H512_APPROVE extends AbstractPfAction<AggZylistVO> {

	public N_H512_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggZylistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggZylistVO> processor = new CompareAroundProcesser<AggZylistVO>(
				Ly_zylistPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggZylistVO[] processBP(Object userObj,
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills) {
		AggZylistVO[] bills = null;
		ILy_zylistMaintain operator = NCLocator.getInstance().lookup(
				ILy_zylistMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
