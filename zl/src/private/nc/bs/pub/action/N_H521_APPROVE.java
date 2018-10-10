package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_carfiles.plugin.bpplugin.Ly_carfilesPluginPoint;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.itf.zl.ILy_carfilesMaintain;

public class N_H521_APPROVE extends AbstractPfAction<AggCarFilesVO> {

	public N_H521_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggCarFilesVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarFilesVO> processor = new CompareAroundProcesser<AggCarFilesVO>(
				Ly_carfilesPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggCarFilesVO[] processBP(Object userObj,
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills) {
		AggCarFilesVO[] bills = null;
		ILy_carfilesMaintain operator = NCLocator.getInstance().lookup(
				ILy_carfilesMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
