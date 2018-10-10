package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.bs.zl.hql_entryacceptance.plugin.bpplugin.Hql_entryacceptancePluginPoint;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_entryacceptanceDeleteBP {

	public void delete(AggEntryacceptanceVO[] bills) {

		DeleteBPTemplate<AggEntryacceptanceVO> bp = new DeleteBPTemplate<AggEntryacceptanceVO>(
				Hql_entryacceptancePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggEntryacceptanceVO> processer) {
		// TODO 前规则
		IRule<AggEntryacceptanceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggEntryacceptanceVO> processer) {
		// TODO 后规则

	}
}
