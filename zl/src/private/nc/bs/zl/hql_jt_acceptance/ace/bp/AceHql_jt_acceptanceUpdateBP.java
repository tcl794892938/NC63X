package nc.bs.zl.hql_jt_acceptance.ace.bp;

import nc.bs.zl.hql_jt_acceptance.plugin.bpplugin.Hql_jt_acceptancePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

/**
 * 修改保存的BP
 * 
 */
public class AceHql_jt_acceptanceUpdateBP {

	public AggJt_acceptanceVO[] update(AggJt_acceptanceVO[] bills,
			AggJt_acceptanceVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggJt_acceptanceVO> bp = new UpdateBPTemplate<AggJt_acceptanceVO>(
				Hql_jt_acceptancePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggJt_acceptanceVO> processer) {
		// TODO 后规则
		IRule<AggJt_acceptanceVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggJt_acceptanceVO> processer) {
		// TODO 前规则
		IRule<AggJt_acceptanceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
