package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

/**
 * ��׼��������BP
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
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggBuildingfileVO> processor) {
		// TODO ���������
		IRule<AggBuildingfileVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggBuildingfileVO> processer) {
		// TODO ����ǰ����
		IRule<AggBuildingfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
