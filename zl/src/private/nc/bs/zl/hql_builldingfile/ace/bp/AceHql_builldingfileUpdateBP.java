package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

/**
 * 修改保存的BP
 * 
 */
public class AceHql_builldingfileUpdateBP {

	public AggBuildingfileVO[] update(AggBuildingfileVO[] bills,
			AggBuildingfileVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggBuildingfileVO> bp = new UpdateBPTemplate<AggBuildingfileVO>(
				Hql_builldingfilePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggBuildingfileVO> processer) {
		// TODO 后规则
		IRule<AggBuildingfileVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggBuildingfileVO> processer) {
		// TODO 前规则
		IRule<AggBuildingfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
