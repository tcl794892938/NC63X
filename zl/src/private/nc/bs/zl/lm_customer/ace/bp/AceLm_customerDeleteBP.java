package nc.bs.zl.lm_customer.ace.bp;

import nc.bs.zl.lm_customer.plugin.bpplugin.Lm_customerPluginPoint;
import nc.vo.zl.lm_customer.AggCustomerVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLm_customerDeleteBP {

	public void delete(AggCustomerVO[] bills) {

		DeleteBPTemplate<AggCustomerVO> bp = new DeleteBPTemplate<AggCustomerVO>(
				Lm_customerPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggCustomerVO> processer) {
		// TODO 前规则
		IRule<AggCustomerVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCustomerVO> processer) {
		// TODO 后规则

	}
}
