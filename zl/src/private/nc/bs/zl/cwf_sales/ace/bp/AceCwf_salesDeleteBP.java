package nc.bs.zl.cwf_sales.ace.bp;

import nc.bs.zl.cwf_sales.plugin.bpplugin.Cwf_salesPluginPoint;
import nc.vo.zl.cwf_sales.AggSalesVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceCwf_salesDeleteBP {

	public void delete(AggSalesVO[] bills) {

		DeleteBPTemplate<AggSalesVO> bp = new DeleteBPTemplate<AggSalesVO>(
				Cwf_salesPluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggSalesVO> processer) {
		// TODO 前规则
		IRule<AggSalesVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggSalesVO> processer) {
		// TODO 后规则

	}
}
