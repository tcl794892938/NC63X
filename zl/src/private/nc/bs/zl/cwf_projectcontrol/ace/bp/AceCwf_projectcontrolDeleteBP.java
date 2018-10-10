package nc.bs.zl.cwf_projectcontrol.ace.bp;

import nc.bs.zl.cwf_projectcontrol.plugin.bpplugin.Cwf_projectcontrolPluginPoint;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceCwf_projectcontrolDeleteBP {

	public void delete(AggProjectcontrol[] bills) {

		DeleteBPTemplate<AggProjectcontrol> bp = new DeleteBPTemplate<AggProjectcontrol>(
				Cwf_projectcontrolPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggProjectcontrol> processer) {
//		// TODO 前规则
//		IRule<AggProjectcontrol> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggProjectcontrol> processer) {
		// TODO 后规则

	}
}
