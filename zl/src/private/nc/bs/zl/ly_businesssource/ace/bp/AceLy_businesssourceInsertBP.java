package nc.bs.zl.ly_businesssource.ace.bp;

import nc.bs.zl.ly_businesssource.plugin.bpplugin.Ly_businesssourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

/**
 * ��׼��������BP
 */
public class AceLy_businesssourceInsertBP {

	public AggBusinessSourceVO[] insert(AggBusinessSourceVO[] bills) {

		InsertBPTemplate<AggBusinessSourceVO> bp = new InsertBPTemplate<AggBusinessSourceVO>(
				Ly_businesssourcePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggBusinessSourceVO> processor) {
		// TODO ���������
		IRule<AggBusinessSourceVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggBusinessSourceVO> processer) {
		// TODO ����ǰ����
		IRule<AggBusinessSourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
