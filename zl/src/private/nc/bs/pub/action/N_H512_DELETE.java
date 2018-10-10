package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_zylist.plugin.bpplugin.Ly_zylistPluginPoint;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.itf.zl.ILy_zylistMaintain;

public class N_H512_DELETE extends AbstractPfAction<AggZylistVO> {

	@Override
	protected CompareAroundProcesser<AggZylistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggZylistVO> processor = new CompareAroundProcesser<AggZylistVO>(
				Ly_zylistPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggZylistVO[] processBP(Object userObj,
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills) {
		ILy_zylistMaintain operator = NCLocator.getInstance().lookup(
				ILy_zylistMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
