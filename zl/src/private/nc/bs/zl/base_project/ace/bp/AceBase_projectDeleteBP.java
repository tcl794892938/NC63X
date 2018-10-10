package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.vo.zl.base_project.AggProjectVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceBase_projectDeleteBP {

	public void delete(AggProjectVO[] bills) {

		DeleteBPTemplate<AggProjectVO> bp = new DeleteBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggProjectVO> processer) {
//		// TODO 前规则
//		IRule<AggProjectVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggProjectVO> processer) {
		// TODO 后规则

	}
}
