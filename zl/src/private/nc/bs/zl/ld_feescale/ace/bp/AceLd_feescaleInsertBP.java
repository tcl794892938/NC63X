package nc.bs.zl.ld_feescale.ace.bp;

import nc.bs.zl.ld_feescale.plugin.bpplugin.Ld_feescalePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

/**
 * ��׼��������BP
 */
public class AceLd_feescaleInsertBP {

	public AggFeescaleVO[] insert(AggFeescaleVO[] bills) {

		InsertBPTemplate<AggFeescaleVO> bp = new InsertBPTemplate<AggFeescaleVO>(
				Ld_feescalePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggFeescaleVO> processor) {
		// TODO ���������
		IRule<AggFeescaleVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggFeescaleVO> processer) {
		// TODO ����ǰ����
		IRule<AggFeescaleVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
