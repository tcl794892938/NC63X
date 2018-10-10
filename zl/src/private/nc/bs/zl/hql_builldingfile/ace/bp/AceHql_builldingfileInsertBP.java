package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

/**
 * 标准单据新增BP
 */
public class AceHql_builldingfileInsertBP {

	public AggBuildingfileVO[] insert(AggBuildingfileVO[] bills) {

		InsertBPTemplate<AggBuildingfileVO> bp = new InsertBPTemplate<AggBuildingfileVO>(
				Hql_builldingfilePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggBuildingfileVO> processor) {
		// TODO 新增后规则
		IRule<AggBuildingfileVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggBuildingfileVO> processer) {
		// TODO 新增前规则
		IRule<AggBuildingfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
