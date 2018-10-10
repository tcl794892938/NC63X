package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.bs.zl.lyw_billconfirmed.plugin.bpplugin.Lyw_billconfirmedPluginPoint;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLyw_billconfirmedDeleteBP {

	public void delete(AggBillconfirmedVO[] bills) {

		DeleteBPTemplate<AggBillconfirmedVO> bp = new DeleteBPTemplate<AggBillconfirmedVO>(
				Lyw_billconfirmedPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBillconfirmedVO> processer) {
		// TODO ǰ����
		IRule<AggBillconfirmedVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBillconfirmedVO> processer) {
		// TODO �����

	}
}
