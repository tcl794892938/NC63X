package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.base_project.AggProjectVO;

/**
 * 修改保存的BP
 * 
 */
public class AceBase_projectUpdateBP {

	public AggProjectVO[] update(AggProjectVO[] bills,
			AggProjectVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggProjectVO> bp = new UpdateBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggProjectVO> processer) {
		// TODO 后规则
		IRule<AggProjectVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggProjectVO> processer) {
		// TODO 前规则
		IRule<AggProjectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
