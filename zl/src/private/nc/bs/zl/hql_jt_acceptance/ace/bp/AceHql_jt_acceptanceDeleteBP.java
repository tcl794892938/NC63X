package nc.bs.zl.hql_jt_acceptance.ace.bp;

import nc.bs.zl.hql_jt_acceptance.plugin.bpplugin.Hql_jt_acceptancePluginPoint;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHql_jt_acceptanceDeleteBP {

	public void delete(AggJt_acceptanceVO[] bills) {

		DeleteBPTemplate<AggJt_acceptanceVO> bp = new DeleteBPTemplate<AggJt_acceptanceVO>(
				Hql_jt_acceptancePluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggJt_acceptanceVO> processer) {
//		// TODO ǰ����
//		IRule<AggJt_acceptanceVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggJt_acceptanceVO> processer) {
		// TODO �����

	}
}
