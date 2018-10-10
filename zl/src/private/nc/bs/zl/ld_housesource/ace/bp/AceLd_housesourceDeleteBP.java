package nc.bs.zl.ld_housesource.ace.bp;

import nc.bs.zl.ld_housesource.plugin.bpplugin.Ld_housesourcePluginPoint;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLd_housesourceDeleteBP {

	public void delete(AggHousesourceVO[] bills) {

		DeleteBPTemplate<AggHousesourceVO> bp = new DeleteBPTemplate<AggHousesourceVO>(
				Ld_housesourcePluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggHousesourceVO> processer) {
//		// TODO 前规则
//		IRule<AggHousesourceVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggHousesourceVO> processer) {
		// TODO 后规则

	}
}
