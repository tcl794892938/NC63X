package nc.bs.zl.cwf_sales.ace.bp;

import nc.bs.zl.cwf_sales.plugin.bpplugin.Cwf_salesPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_sales.AggSalesVO;

/**
 * �޸ı����BP
 * 
 */
public class AceCwf_salesUpdateBP {

	public AggSalesVO[] update(AggSalesVO[] bills,
			AggSalesVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggSalesVO> bp = new UpdateBPTemplate<AggSalesVO>(
				Cwf_salesPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggSalesVO> processer) {
		// TODO �����
		IRule<AggSalesVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggSalesVO> processer) {
		// TODO ǰ����
		IRule<AggSalesVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
