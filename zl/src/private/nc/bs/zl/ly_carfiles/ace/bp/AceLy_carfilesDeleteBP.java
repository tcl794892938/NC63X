package nc.bs.zl.ly_carfiles.ace.bp;

import nc.bs.zl.ly_carfiles.plugin.bpplugin.Ly_carfilesPluginPoint;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLy_carfilesDeleteBP {

	public void delete(AggCarFilesVO[] bills) {

		DeleteBPTemplate<AggCarFilesVO> bp = new DeleteBPTemplate<AggCarFilesVO>(
				Ly_carfilesPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggCarFilesVO> processer) {
		// TODO 前规则
		IRule<AggCarFilesVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggCarFilesVO> processer) {
		// TODO 后规则

	}
}
