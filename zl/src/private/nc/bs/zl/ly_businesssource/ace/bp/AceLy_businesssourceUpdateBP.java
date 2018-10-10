package nc.bs.zl.ly_businesssource.ace.bp;

import nc.bs.zl.ly_businesssource.plugin.bpplugin.Ly_businesssourcePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLy_businesssourceUpdateBP {

	public AggBusinessSourceVO[] update(AggBusinessSourceVO[] bills,
			AggBusinessSourceVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggBusinessSourceVO> bp = new UpdateBPTemplate<AggBusinessSourceVO>(
				Ly_businesssourcePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggBusinessSourceVO> processer) {
		// TODO �����
		IRule<AggBusinessSourceVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggBusinessSourceVO> processer) {
		// TODO ǰ����
		IRule<AggBusinessSourceVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
