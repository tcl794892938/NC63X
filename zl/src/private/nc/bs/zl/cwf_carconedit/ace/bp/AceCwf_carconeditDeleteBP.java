package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceCwf_carconeditDeleteBP {

	public void delete(AggCarconeditVO[] bills) {

		DeleteBPTemplate<AggCarconeditVO> bp = new DeleteBPTemplate<AggCarconeditVO>(
				Cwf_carconeditPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCarconeditVO> processer) {
		// TODO ǰ����
		IRule<AggCarconeditVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCarconeditVO> processer) {
		// TODO �����

	}
}
