package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHql_builldingfileDeleteBP {

	public void delete(AggBuildingfileVO[] bills) {

		DeleteBPTemplate<AggBuildingfileVO> bp = new DeleteBPTemplate<AggBuildingfileVO>(
				Hql_builldingfilePluginPoint.DELETE);
		// 增加执行前规则
		//this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggBuildingfileVO> processer) {
//		// TODO 前规则
//		IRule<AggBuildingfileVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggBuildingfileVO> processer) {
		// TODO 后规则

	}
}
