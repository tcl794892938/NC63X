package nc.bs.zl.ly_businesssource.ace.bp;

import nc.bs.zl.ly_businesssource.plugin.bpplugin.Ly_businesssourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

/**
 * 修改保存的BP
 * 
 */
public class AceLy_businesssourceUpdateBP {

	public AggBusinessSourceVO[] update(AggBusinessSourceVO[] bills,
			AggBusinessSourceVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggBusinessSourceVO> bp = new UpdateBPTemplate<AggBusinessSourceVO>(
				Ly_businesssourcePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggBusinessSourceVO> processer) {
		// TODO 后规则
		IRule<AggBusinessSourceVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggBusinessSourceVO> processer) {
		// TODO 前规则
		IRule<AggBusinessSourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
