package nc.bs.zl.cwf_recbill.ace.bp;

import nc.bs.zl.cwf_recbill.plugin.bpplugin.Cwf_recbillPluginPoint;
import nc.vo.zl.cwf_recbill.AggRecbillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceCwf_recbillDeleteBP {

	public void delete(AggRecbillVO[] bills) {

		DeleteBPTemplate<AggRecbillVO> bp = new DeleteBPTemplate<AggRecbillVO>(
				Cwf_recbillPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggRecbillVO> processer) {
		// TODO ǰ����
		IRule<AggRecbillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggRecbillVO> processer) {
		// TODO �����

	}
}
