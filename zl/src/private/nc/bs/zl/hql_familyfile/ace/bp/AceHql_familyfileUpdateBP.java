package nc.bs.zl.hql_familyfile.ace.bp;

import nc.bs.zl.hql_familyfile.plugin.bpplugin.Hql_familyfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

/**
 * 修改保存的BP
 * 
 */
public class AceHql_familyfileUpdateBP {

	public AggFamilyfileVO[] update(AggFamilyfileVO[] bills,
			AggFamilyfileVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggFamilyfileVO> bp = new UpdateBPTemplate<AggFamilyfileVO>(
				Hql_familyfilePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggFamilyfileVO> processer) {
		// TODO 后规则
		IRule<AggFamilyfileVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggFamilyfileVO> processer) {
		// TODO 前规则
		IRule<AggFamilyfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
