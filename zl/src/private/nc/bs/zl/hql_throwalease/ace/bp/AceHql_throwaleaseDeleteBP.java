package nc.bs.zl.hql_throwalease.ace.bp;

import nc.bs.zl.hql_throwalease.plugin.bpplugin.Hql_throwaleasePluginPoint;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHql_throwaleaseDeleteBP {

	public void delete(AggThrowaleaseVO[] bills) {

		DeleteBPTemplate<AggThrowaleaseVO> bp = new DeleteBPTemplate<AggThrowaleaseVO>(
				Hql_throwaleasePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggThrowaleaseVO> processer) {
		// TODO ǰ����
		IRule<AggThrowaleaseVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggThrowaleaseVO> processer) {
		// TODO �����

	}
}
