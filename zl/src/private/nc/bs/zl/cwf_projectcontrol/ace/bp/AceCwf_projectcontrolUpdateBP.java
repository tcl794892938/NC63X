package nc.bs.zl.cwf_projectcontrol.ace.bp;

import nc.bs.zl.cwf_projectcontrol.plugin.bpplugin.Cwf_projectcontrolPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

/**
 * 修改保存的BP
 * 
 */
public class AceCwf_projectcontrolUpdateBP {

	public AggProjectcontrol[] update(AggProjectcontrol[] bills,
			AggProjectcontrol[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggProjectcontrol> bp = new UpdateBPTemplate<AggProjectcontrol>(
				Cwf_projectcontrolPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggProjectcontrol> processer) {
		// TODO 后规则
		IRule<AggProjectcontrol> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggProjectcontrol> processer) {
		// TODO 前规则
		IRule<AggProjectcontrol> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
