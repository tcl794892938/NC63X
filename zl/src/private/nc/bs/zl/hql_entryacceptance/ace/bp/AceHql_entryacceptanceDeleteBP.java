package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.bs.zl.hql_entryacceptance.plugin.bpplugin.Hql_entryacceptancePluginPoint;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHql_entryacceptanceDeleteBP {

	public void delete(AggEntryacceptanceVO[] bills) {

		DeleteBPTemplate<AggEntryacceptanceVO> bp = new DeleteBPTemplate<AggEntryacceptanceVO>(
				Hql_entryacceptancePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggEntryacceptanceVO> processer) {
		// TODO ǰ����
		IRule<AggEntryacceptanceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggEntryacceptanceVO> processer) {
		// TODO �����

	}
}
