package nc.bs.zl.lm_customer.ace.bp;

import nc.bs.zl.lm_customer.plugin.bpplugin.Lm_customerPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.lm_customer.AggCustomerVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLm_customerUpdateBP {

	public AggCustomerVO[] update(AggCustomerVO[] bills,
			AggCustomerVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggCustomerVO> bp = new UpdateBPTemplate<AggCustomerVO>(
				Lm_customerPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggCustomerVO> processer) {
		// TODO �����
		IRule<AggCustomerVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggCustomerVO> processer) {
		// TODO ǰ����
		IRule<AggCustomerVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
