package nc.bs.zl.hql_builldingfile.ace.bp;

import nc.bs.zl.hql_builldingfile.plugin.bpplugin.Hql_builldingfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

/**
 * �޸ı����BP
 * 
 */
public class AceHql_builldingfileUpdateBP {

	public AggBuildingfileVO[] update(AggBuildingfileVO[] bills,
			AggBuildingfileVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggBuildingfileVO> bp = new UpdateBPTemplate<AggBuildingfileVO>(
				Hql_builldingfilePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggBuildingfileVO> processer) {
		// TODO �����
		IRule<AggBuildingfileVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggBuildingfileVO> processer) {
		// TODO ǰ����
		IRule<AggBuildingfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
