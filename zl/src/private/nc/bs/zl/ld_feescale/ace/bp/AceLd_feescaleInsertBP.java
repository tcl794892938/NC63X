package nc.bs.zl.ld_feescale.ace.bp;

import nc.bs.zl.ld_feescale.plugin.bpplugin.Ld_feescalePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

/**
 * 标准单据新增BP
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
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggFeescaleVO> processor) {
		// TODO 新增后规则
		IRule<AggFeescaleVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggFeescaleVO> processer) {
		// TODO 新增前规则
		IRule<AggFeescaleVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
