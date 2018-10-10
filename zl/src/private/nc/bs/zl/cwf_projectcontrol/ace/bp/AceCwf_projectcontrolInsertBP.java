package nc.bs.zl.cwf_projectcontrol.ace.bp;

import nc.bs.zl.cwf_projectcontrol.plugin.bpplugin.Cwf_projectcontrolPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

/**
 * 标准单据新增BP
 */
public class AceCwf_projectcontrolInsertBP {

	public AggProjectcontrol[] insert(AggProjectcontrol[] bills) {

		InsertBPTemplate<AggProjectcontrol> bp = new InsertBPTemplate<AggProjectcontrol>(
				Cwf_projectcontrolPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggProjectcontrol> processor) {
		// TODO 新增后规则
		IRule<AggProjectcontrol> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggProjectcontrol> processer) {
		// TODO 新增前规则
		IRule<AggProjectcontrol> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
