package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.bs.zl.ld_parkcontract.plugin.bpplugin.Ld_parkcontractPluginPoint;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_parkcontractDeleteBP {

	public void delete(AggParkcontractVO[] bills) {

		DeleteBPTemplate<AggParkcontractVO> bp = new DeleteBPTemplate<AggParkcontractVO>(
				Ld_parkcontractPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggParkcontractVO> processer) {
		// TODO ǰ����
		IRule<AggParkcontractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggParkcontractVO> processer) {
		// TODO �����

	}
}
