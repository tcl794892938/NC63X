package nc.bs.zl.cwf_sales.ace.bp;

import nc.bs.zl.cwf_sales.plugin.bpplugin.Cwf_salesPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_sales.AggSalesVO;

/**
 * ��׼��������BP
 */
public class AceCwf_salesInsertBP {

	public AggSalesVO[] insert(AggSalesVO[] bills) {

		InsertBPTemplate<AggSalesVO> bp = new InsertBPTemplate<AggSalesVO>(
				Cwf_salesPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggSalesVO> processor) {
		// TODO ���������
		IRule<AggSalesVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggSalesVO> processer) {
		// TODO ����ǰ����
		IRule<AggSalesVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
