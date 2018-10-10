package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.base_project.AggProjectVO;

/**
 * �޸ı����BP
 * 
 */
public class AceBase_projectUpdateBP {

	public AggProjectVO[] update(AggProjectVO[] bills,
			AggProjectVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggProjectVO> bp = new UpdateBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggProjectVO> processer) {
		// TODO �����
		IRule<AggProjectVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggProjectVO> processer) {
		// TODO ǰ����
		IRule<AggProjectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
