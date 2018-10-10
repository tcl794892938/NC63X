package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_mdcontract.plugin.bpplugin.Ld_mdcontractPluginPoint;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.itf.zl.ILd_mdcontractMaintain;

public class N_H440_UNSAVEBILL extends AbstractPfAction<AggMdcontractVO> {

	@Override
	protected CompareAroundProcesser<AggMdcontractVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggMdcontractVO> processor = new CompareAroundProcesser<AggMdcontractVO>(
				Ld_mdcontractPluginPoint.UNSEND_APPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggMdcontractVO[] processBP(Object userObj,
			AggMdcontractVO[] clientFullVOs, AggMdcontractVO[] originBills) {
		ILd_mdcontractMaintain operator = NCLocator.getInstance().lookup(
				ILd_mdcontractMaintain.class);
		AggMdcontractVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
