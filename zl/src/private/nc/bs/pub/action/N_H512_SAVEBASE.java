package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_zylist.plugin.bpplugin.Ly_zylistPluginPoint;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.itf.zl.ILy_zylistMaintain;

public class N_H512_SAVEBASE extends AbstractPfAction<AggZylistVO> {

	@Override
	protected CompareAroundProcesser<AggZylistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggZylistVO> processor = null;
		AggZylistVO[] clientFullVOs = (AggZylistVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggZylistVO>(
					Ly_zylistPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggZylistVO>(
					Ly_zylistPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggZylistVO> rule = null;

		return processor;
	}

	@Override
	protected AggZylistVO[] processBP(Object userObj,
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills) {

		AggZylistVO[] bills = null;
		try {
			ILy_zylistMaintain operator = NCLocator.getInstance()
					.lookup(ILy_zylistMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
