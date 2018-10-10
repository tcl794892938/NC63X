package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.zl.ly_carfiles.plugin.bpplugin.Ly_carfilesPluginPoint;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.itf.zl.ILy_carfilesMaintain;

public class N_H521_DELETE extends AbstractPfAction<AggCarFilesVO> {

	@Override
	protected CompareAroundProcesser<AggCarFilesVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCarFilesVO> processor = new CompareAroundProcesser<AggCarFilesVO>(
				Ly_carfilesPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggCarFilesVO[] processBP(Object userObj,
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills) {
		ILy_carfilesMaintain operator = NCLocator.getInstance().lookup(
				ILy_carfilesMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
