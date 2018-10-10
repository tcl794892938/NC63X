package nc.bs.zl.ld_carcontract.ace.bp;

import nc.bs.zl.ld_carcontract.plugin.bpplugin.Ld_carcontractPluginPoint;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_carcontractDeleteBP {

	public void delete(AggCarcontractVO[] bills) {

		DeleteBPTemplate<AggCarcontractVO> bp = new DeleteBPTemplate<AggCarcontractVO>(
				Ld_carcontractPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCarcontractVO> processer) {
		// TODO ǰ����
		IRule<AggCarcontractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCarcontractVO> processer) {
		// TODO �����

	}
}
