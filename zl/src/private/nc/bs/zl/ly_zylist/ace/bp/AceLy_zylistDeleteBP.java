package nc.bs.zl.ly_zylist.ace.bp;

import nc.bs.zl.ly_zylist.plugin.bpplugin.Ly_zylistPluginPoint;
import nc.vo.zl.ly_zylist.AggZylistVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLy_zylistDeleteBP {

	public void delete(AggZylistVO[] bills) {

		DeleteBPTemplate<AggZylistVO> bp = new DeleteBPTemplate<AggZylistVO>(
				Ly_zylistPluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggZylistVO> processer) {
		// TODO ǰ����
		IRule<AggZylistVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggZylistVO> processer) {
		// TODO �����

	}
}
