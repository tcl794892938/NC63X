package nc.bs.zl.lm_customer.ace.bp;

import nc.bs.zl.lm_customer.plugin.bpplugin.Lm_customerPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.lm_customer.AggCustomerVO;

/**
 * 标准单据新增BP
 */
public class AceLm_customerInsertBP {

	public AggCustomerVO[] insert(AggCustomerVO[] bills) {

		InsertBPTemplate<AggCustomerVO> bp = new InsertBPTemplate<AggCustomerVO>(
				Lm_customerPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggCustomerVO> processor) {
		// TODO 新增后规则
		IRule<AggCustomerVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggCustomerVO> processer) {
		// TODO 新增前规则
		IRule<AggCustomerVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
