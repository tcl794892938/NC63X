package nc.bs.zl.ly_pocustomers.ace.bp;

import nc.bs.zl.ly_pocustomers.plugin.bpplugin.Ly_pocustomersPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLy_pocustomersUpdateBP {

	public AggPocustomersVO[] update(AggPocustomersVO[] bills,
			AggPocustomersVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggPocustomersVO> bp = new UpdateBPTemplate<AggPocustomersVO>(
				Ly_pocustomersPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggPocustomersVO> processer) {
		// TODO �����
		IRule<AggPocustomersVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggPocustomersVO> processer) {
		// TODO ǰ����
		IRule<AggPocustomersVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
