package nc.bs.zl.hql_payment.ace.bp;

import nc.bs.zl.hql_payment.plugin.bpplugin.Hql_paymentPluginPoint;
import nc.vo.zl.hql_payment.AggPaymentVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_paymentDeleteBP {

	public void delete(AggPaymentVO[] bills) {

		DeleteBPTemplate<AggPaymentVO> bp = new DeleteBPTemplate<AggPaymentVO>(
				Hql_paymentPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggPaymentVO> processer) {
		// TODO 前规则
		IRule<AggPaymentVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggPaymentVO> processer) {
		// TODO 后规则

	}
}
