package nc.bs.zl.ld_upcontract.ace.bp;

import nc.bs.zl.ld_upcontract.plugin.bpplugin.Ld_upcontractPluginPoint;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_upcontractDeleteBP {

	public void delete(AggUpcontractVO[] bills) {

		DeleteBPTemplate<AggUpcontractVO> bp = new DeleteBPTemplate<AggUpcontractVO>(
				Ld_upcontractPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggUpcontractVO> processer) {
		// TODO ǰ����
		IRule<AggUpcontractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggUpcontractVO> processer) {
		// TODO �����

	}
}
