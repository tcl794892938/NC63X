package nc.bs.zl.cwf_sales.ace.bp;

import nc.bs.zl.cwf_sales.plugin.bpplugin.Cwf_salesPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_sales.AggSalesVO;

/**
 * 修改保存的BP
 * 
 */
public class AceCwf_salesUpdateBP {

	public AggSalesVO[] update(AggSalesVO[] bills,
			AggSalesVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggSalesVO> bp = new UpdateBPTemplate<AggSalesVO>(
				Cwf_salesPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSalesVO> processer) {
		// TODO 后规则
		IRule<AggSalesVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggSalesVO> processer) {
		// TODO 前规则
		IRule<AggSalesVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
