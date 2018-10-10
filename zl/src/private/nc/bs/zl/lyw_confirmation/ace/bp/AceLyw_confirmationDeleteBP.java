package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.bs.zl.lyw_confirmation.plugin.bpplugin.Lyw_confirmationPluginPoint;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLyw_confirmationDeleteBP {

	public void delete(AggConfirmationVO[] bills) {

		DeleteBPTemplate<AggConfirmationVO> bp = new DeleteBPTemplate<AggConfirmationVO>(
				Lyw_confirmationPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggConfirmationVO> processer) {
		// TODO 前规则
		IRule<AggConfirmationVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggConfirmationVO> processer) {
		// TODO 后规则

	}
}
