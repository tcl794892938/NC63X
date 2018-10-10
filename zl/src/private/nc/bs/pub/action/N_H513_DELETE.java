package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.itf.zl.ILy_hflistMaintain;

public class N_H513_DELETE extends AbstractPfAction<AggHflistVO> {

	@Override
	protected CompareAroundProcesser<AggHflistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggHflistVO> processor = new CompareAroundProcesser<AggHflistVO>(
				Ly_hflistPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggHflistVO[] processBP(Object userObj,
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills) {
		ILy_hflistMaintain operator = NCLocator.getInstance().lookup(
				ILy_hflistMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
