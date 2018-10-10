package nc.bs.zl.hql_throwalease.ace.bp;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_throwaleaseDeleteBP {

	public void delete(AggThrowaleaseVO[] bills) {

		DeleteBPTemplate<AggThrowaleaseVO> bp = new DeleteBPTemplate<AggThrowaleaseVO>(
				Hql_throwaleasePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggThrowaleaseVO> processer) {
		// TODO 前规则
		IRule<AggThrowaleaseVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggThrowaleaseVO> processer) {
		// TODO 后规则

	}
}
