package nc.bs.zl.hql_jt_acceptance.ace.bp;

import nc.bs.zl.hql_jt_acceptance.plugin.bpplugin.Hql_jt_acceptancePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

/**
 * ��׼��������BP
 */
public class AceHql_jt_acceptanceInsertBP {

	public AggJt_acceptanceVO[] insert(AggJt_acceptanceVO[] bills) {

		InsertBPTemplate<AggJt_acceptanceVO> bp = new InsertBPTemplate<AggJt_acceptanceVO>(
				Hql_jt_acceptancePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggJt_acceptanceVO> processor) {
		// TODO ���������
		IRule<AggJt_acceptanceVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggJt_acceptanceVO> processer) {
		// TODO ����ǰ����
		IRule<AggJt_acceptanceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
