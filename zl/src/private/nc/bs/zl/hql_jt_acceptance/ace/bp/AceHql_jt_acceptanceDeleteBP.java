package nc.bs.zl.hql_jt_acceptance.ace.bp;

import nc.bs.zl.hql_jt_acceptance.plugin.bpplugin.Hql_jt_acceptancePluginPoint;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_jt_acceptanceDeleteBP {

	public void delete(AggJt_acceptanceVO[] bills) {

		DeleteBPTemplate<AggJt_acceptanceVO> bp = new DeleteBPTemplate<AggJt_acceptanceVO>(
				Hql_jt_acceptancePluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggJt_acceptanceVO> processer) {
//		// TODO 前规则
//		IRule<AggJt_acceptanceVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggJt_acceptanceVO> processer) {
		// TODO 后规则

	}
}
