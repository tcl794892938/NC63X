package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.bs.zl.lyw_billconfirmed.plugin.bpplugin.Lyw_billconfirmedPluginPoint;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLyw_billconfirmedDeleteBP {

	public void delete(AggBillconfirmedVO[] bills) {

		DeleteBPTemplate<AggBillconfirmedVO> bp = new DeleteBPTemplate<AggBillconfirmedVO>(
				Lyw_billconfirmedPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggBillconfirmedVO> processer) {
		// TODO 前规则
		IRule<AggBillconfirmedVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBillconfirmedVO> processer) {
		// TODO 后规则

	}
}
