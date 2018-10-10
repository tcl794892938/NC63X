package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_zylist.plugin.bpplugin.Ly_zylistPluginPoint;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.itf.zl.ILy_zylistMaintain;

public class N_H512_UNAPPROVE extends AbstractPfAction<AggZylistVO> {

	@Override
	protected CompareAroundProcesser<AggZylistVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggZylistVO> processor = new CompareAroundProcesser<AggZylistVO>(
				Ly_zylistPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggZylistVO[] processBP(Object userObj,
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggZylistVO[] bills = null;
		try {
			ILy_zylistMaintain operator = NCLocator.getInstance()
					.lookup(ILy_zylistMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
