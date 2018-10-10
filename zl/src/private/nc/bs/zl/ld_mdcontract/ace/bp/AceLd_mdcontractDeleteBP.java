package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.bs.zl.ld_mdcontract.plugin.bpplugin.Ld_mdcontractPluginPoint;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLd_mdcontractDeleteBP {

	public void delete(AggMdcontractVO[] bills) {

		DeleteBPTemplate<AggMdcontractVO> bp = new DeleteBPTemplate<AggMdcontractVO>(
				Ld_mdcontractPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggMdcontractVO> processer) {
		// TODO 前规则
		IRule<AggMdcontractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggMdcontractVO> processer) {
		// TODO 后规则

	}
}
