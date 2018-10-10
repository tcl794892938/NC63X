package nc.bs.zl.ld_housesource.ace.bp;

import nc.bs.zl.ld_housesource.plugin.bpplugin.Ld_housesourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

/**
 * 修改保存的BP
 * 
 */
public class AceLd_housesourceUpdateBP {

	public AggHousesourceVO[] update(AggHousesourceVO[] bills,
			AggHousesourceVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggHousesourceVO> bp = new UpdateBPTemplate<AggHousesourceVO>(
				Ld_housesourcePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggHousesourceVO> processer) {
		// TODO 后规则
		IRule<AggHousesourceVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggHousesourceVO> processer) {
		// TODO 前规则
		IRule<AggHousesourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
