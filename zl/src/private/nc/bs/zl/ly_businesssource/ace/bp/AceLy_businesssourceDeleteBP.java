package nc.bs.zl.ly_businesssource.ace.bp;

import nc.bs.zl.ly_businesssource.plugin.bpplugin.Ly_businesssourcePluginPoint;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLy_businesssourceDeleteBP {

	public void delete(AggBusinessSourceVO[] bills) {

		DeleteBPTemplate<AggBusinessSourceVO> bp = new DeleteBPTemplate<AggBusinessSourceVO>(
				Ly_businesssourcePluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggBusinessSourceVO> processer) {
		// TODO 前规则
		IRule<AggBusinessSourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBusinessSourceVO> processer) {
		// TODO 后规则

	}
}
