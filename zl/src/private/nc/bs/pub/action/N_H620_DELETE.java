package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_recbill.plugin.bpplugin.Cwf_recbillPluginPoint;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.itf.zl.ICwf_recbillMaintain;

public class N_H620_DELETE extends AbstractPfAction<AggRecbillVO> {

	@Override
	protected CompareAroundProcesser<AggRecbillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggRecbillVO> processor = new CompareAroundProcesser<AggRecbillVO>(
				Cwf_recbillPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggRecbillVO[] processBP(Object userObj,
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills) {
		ICwf_recbillMaintain operator = NCLocator.getInstance().lookup(
				ICwf_recbillMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
