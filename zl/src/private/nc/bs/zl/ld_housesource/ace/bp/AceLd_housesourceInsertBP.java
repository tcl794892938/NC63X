package nc.bs.zl.ld_housesource.ace.bp;

import nc.bs.zl.ld_housesource.plugin.bpplugin.Ld_housesourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

/**
 * ��׼��������BP
 */
public class AceLd_housesourceInsertBP {

	public AggHousesourceVO[] insert(AggHousesourceVO[] bills) {

		InsertBPTemplate<AggHousesourceVO> bp = new InsertBPTemplate<AggHousesourceVO>(
				Ld_housesourcePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggHousesourceVO> processor) {
		// TODO ���������
		IRule<AggHousesourceVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggHousesourceVO> processer) {
		// TODO ����ǰ����
		IRule<AggHousesourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
