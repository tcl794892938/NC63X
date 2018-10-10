package nc.bs.zl.ly_pocustomers.ace.bp;

import nc.bs.zl.ly_pocustomers.plugin.bpplugin.Ly_pocustomersPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

/**
 * ��׼��������BP
 */
public class AceLy_pocustomersInsertBP {

	public AggPocustomersVO[] insert(AggPocustomersVO[] bills) {

		InsertBPTemplate<AggPocustomersVO> bp = new InsertBPTemplate<AggPocustomersVO>(
				Ly_pocustomersPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggPocustomersVO> processor) {
		// TODO ���������
		IRule<AggPocustomersVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggPocustomersVO> processer) {
		// TODO ����ǰ����
		IRule<AggPocustomersVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
