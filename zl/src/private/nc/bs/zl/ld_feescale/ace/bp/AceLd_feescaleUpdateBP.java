package nc.bs.zl.ld_feescale.ace.bp;

import nc.bs.zl.ld_feescale.plugin.bpplugin.Ld_feescalePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

/**
 * �޸ı����BP
 * 
 */
public class AceLd_feescaleUpdateBP {

	public AggFeescaleVO[] update(AggFeescaleVO[] bills,
			AggFeescaleVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggFeescaleVO> bp = new UpdateBPTemplate<AggFeescaleVO>(
				Ld_feescalePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggFeescaleVO> processer) {
		// TODO �����
		IRule<AggFeescaleVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggFeescaleVO> processer) {
		// TODO ǰ����
		IRule<AggFeescaleVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
