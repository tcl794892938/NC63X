package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ld_kpregister.plugin.bpplugin.Ld_kpregisterPluginPoint;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.itf.zl.ILd_kpregisterMaintain;

public class N_H650_DELETE extends AbstractPfAction<AggKpregisterVO> {

	@Override
	protected CompareAroundProcesser<AggKpregisterVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggKpregisterVO> processor = new CompareAroundProcesser<AggKpregisterVO>(
				Ld_kpregisterPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggKpregisterVO[] processBP(Object userObj,
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills) {
		ILd_kpregisterMaintain operator = NCLocator.getInstance().lookup(
				ILd_kpregisterMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
