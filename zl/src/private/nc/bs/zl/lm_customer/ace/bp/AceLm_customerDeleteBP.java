package nc.bs.zl.lm_customer.ace.bp;

import nc.bs.zl.lm_customer.plugin.bpplugin.Lm_customerPluginPoint;
import nc.vo.zl.lm_customer.AggCustomerVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLm_customerDeleteBP {

	public void delete(AggCustomerVO[] bills) {

		DeleteBPTemplate<AggCustomerVO> bp = new DeleteBPTemplate<AggCustomerVO>(
				Lm_customerPluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggCustomerVO> processer) {
		// TODO ǰ����
		IRule<AggCustomerVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCustomerVO> processer) {
		// TODO �����

	}
}
