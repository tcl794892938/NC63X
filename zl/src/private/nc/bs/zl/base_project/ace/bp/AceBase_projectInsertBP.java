package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.base_project.AggProjectVO;

/**
 * 标准单据新增BP
 */
public class AceBase_projectInsertBP {

	public AggProjectVO[] insert(AggProjectVO[] bills) {

		InsertBPTemplate<AggProjectVO> bp = new InsertBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggProjectVO> processor) {
		// TODO 新增后规则
		IRule<AggProjectVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggProjectVO> processer) {
		// TODO 新增前规则
		IRule<AggProjectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
