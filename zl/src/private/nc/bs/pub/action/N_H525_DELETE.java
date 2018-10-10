package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.itf.zl.ICwf_carconeditMaintain;

public class N_H525_DELETE extends AbstractPfAction<AggCarconeditVO> {

	@Override
	protected CompareAroundProcesser<AggCarconeditVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarconeditVO> processor = new CompareAroundProcesser<AggCarconeditVO>(
				Cwf_carconeditPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggCarconeditVO[] processBP(Object userObj,
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills) {
		ICwf_carconeditMaintain operator = NCLocator.getInstance().lookup(
				ICwf_carconeditMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
