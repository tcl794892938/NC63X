package nc.bs.zl.hql_prepayment.ace.bp;

import nc.bs.zl.hql_prepayment.plugin.bpplugin.Hql_prepaymentPluginPoint;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_prepaymentDeleteBP {

	public void delete(AggPrepaymentVO[] bills) {

		DeleteBPTemplate<AggPrepaymentVO> bp = new DeleteBPTemplate<AggPrepaymentVO>(
				Hql_prepaymentPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggPrepaymentVO> processer) {
		// TODO 前规则
		IRule<AggPrepaymentVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggPrepaymentVO> processer) {
		// TODO 后规则

	}
}
