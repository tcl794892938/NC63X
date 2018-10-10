package nc.bs.zl.ld_feescale.ace.bp;

import nc.bs.zl.ld_feescale.plugin.bpplugin.Ld_feescalePluginPoint;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceLd_feescaleDeleteBP {

	public void delete(AggFeescaleVO[] bills) {

		DeleteBPTemplate<AggFeescaleVO> bp = new DeleteBPTemplate<AggFeescaleVO>(
				Ld_feescalePluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggFeescaleVO> processer) {
//		// TODO 前规则
//		IRule<AggFeescaleVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggFeescaleVO> processer) {
		// TODO 后规则

	}
}
