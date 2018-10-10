package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.bs.zl.cwf_carconedit.plugin.bpplugin.Cwf_carconeditPluginPoint;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceCwf_carconeditDeleteBP {

	public void delete(AggCarconeditVO[] bills) {

		DeleteBPTemplate<AggCarconeditVO> bp = new DeleteBPTemplate<AggCarconeditVO>(
				Cwf_carconeditPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCarconeditVO> processer) {
		// TODO 前规则
		IRule<AggCarconeditVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCarconeditVO> processer) {
		// TODO 后规则

	}
}
