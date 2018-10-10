package nc.bs.zl.ly_pocustomers.ace.bp;

import nc.bs.zl.ly_pocustomers.plugin.bpplugin.Ly_pocustomersPluginPoint;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLy_pocustomersDeleteBP {

	public void delete(AggPocustomersVO[] bills) {

		DeleteBPTemplate<AggPocustomersVO> bp = new DeleteBPTemplate<AggPocustomersVO>(
				Ly_pocustomersPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggPocustomersVO> processer) {
		// TODO 前规则
		IRule<AggPocustomersVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggPocustomersVO> processer) {
		// TODO 后规则

	}
}
