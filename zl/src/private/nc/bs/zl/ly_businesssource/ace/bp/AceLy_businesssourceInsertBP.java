package nc.bs.zl.ly_businesssource.ace.bp;

import nc.bs.zl.ly_businesssource.plugin.bpplugin.Ly_businesssourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

/**
 * 标准单据新增BP
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
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggBusinessSourceVO> processor) {
		// TODO 新增后规则
		IRule<AggBusinessSourceVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggBusinessSourceVO> processer) {
		// TODO 新增前规则
		IRule<AggBusinessSourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
