package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_gather.plugin.bpplugin.Cwf_gatherPluginPoint;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.itf.zl.ICwf_gatherMaintain;

public class N_H630_DELETE extends AbstractPfAction<AggGatherVO> {

	@Override
	protected CompareAroundProcesser<AggGatherVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggGatherVO> processor = new CompareAroundProcesser<AggGatherVO>(
				Cwf_gatherPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggGatherVO[] processBP(Object userObj,
			AggGatherVO[] clientFullVOs, AggGatherVO[] originBills) {
		ICwf_gatherMaintain operator = NCLocator.getInstance().lookup(
				ICwf_gatherMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
