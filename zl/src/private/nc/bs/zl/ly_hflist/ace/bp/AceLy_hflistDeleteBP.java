package nc.bs.zl.ly_hflist.ace.bp;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLy_hflistDeleteBP {

	public void delete(AggHflistVO[] bills) {

		DeleteBPTemplate<AggHflistVO> bp = new DeleteBPTemplate<AggHflistVO>(
				Ly_hflistPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggHflistVO> processer) {
		// TODO 前规则
		IRule<AggHflistVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggHflistVO> processer) {
		// TODO 后规则

	}
}
