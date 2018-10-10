package nc.bs.zl.ly_pocustomers.ace.bp;

import nc.bs.zl.ly_pocustomers.plugin.bpplugin.Ly_pocustomersPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

/**
 * 修改保存的BP
 * 
 */
public class AceLy_pocustomersUpdateBP {

	public AggPocustomersVO[] update(AggPocustomersVO[] bills,
			AggPocustomersVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggPocustomersVO> bp = new UpdateBPTemplate<AggPocustomersVO>(
				Ly_pocustomersPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggPocustomersVO> processer) {
		// TODO 后规则
		IRule<AggPocustomersVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggPocustomersVO> processer) {
		// TODO 前规则
		IRule<AggPocustomersVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
