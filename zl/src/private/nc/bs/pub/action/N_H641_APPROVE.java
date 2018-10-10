package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.lyw_billconfirmed.plugin.bpplugin.Lyw_billconfirmedPluginPoint;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.itf.zl.ILyw_billconfirmedMaintain;

public class N_H641_APPROVE extends AbstractPfAction<AggBillconfirmedVO> {

	public N_H641_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggBillconfirmedVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggBillconfirmedVO> processor = new CompareAroundProcesser<AggBillconfirmedVO>(
				Lyw_billconfirmedPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggBillconfirmedVO[] processBP(Object userObj,
			AggBillconfirmedVO[] clientFullVOs, AggBillconfirmedVO[] originBills) {
		AggBillconfirmedVO[] bills = null;
		ILyw_billconfirmedMaintain operator = NCLocator.getInstance().lookup(
				ILyw_billconfirmedMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
