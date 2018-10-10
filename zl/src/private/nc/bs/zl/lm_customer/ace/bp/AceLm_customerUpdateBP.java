package nc.bs.zl.lm_customer.ace.bp;

import nc.bs.zl.lm_customer.plugin.bpplugin.Lm_customerPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.lm_customer.AggCustomerVO;

/**
 * 修改保存的BP
 * 
 */
public class AceLm_customerUpdateBP {

	public AggCustomerVO[] update(AggCustomerVO[] bills,
			AggCustomerVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggCustomerVO> bp = new UpdateBPTemplate<AggCustomerVO>(
				Lm_customerPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggCustomerVO> processer) {
		// TODO 后规则
		IRule<AggCustomerVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggCustomerVO> processer) {
		// TODO 前规则
		IRule<AggCustomerVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
