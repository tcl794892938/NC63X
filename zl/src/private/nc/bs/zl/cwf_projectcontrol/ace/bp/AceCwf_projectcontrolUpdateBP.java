package nc.bs.zl.cwf_projectcontrol.ace.bp;

import nc.bs.zl.cwf_projectcontrol.plugin.bpplugin.Cwf_projectcontrolPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

/**
 * �޸ı����BP
 * 
 */
public class AceCwf_projectcontrolUpdateBP {

	public AggProjectcontrol[] update(AggProjectcontrol[] bills,
			AggProjectcontrol[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggProjectcontrol> bp = new UpdateBPTemplate<AggProjectcontrol>(
				Cwf_projectcontrolPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggProjectcontrol> processer) {
		// TODO �����
		IRule<AggProjectcontrol> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggProjectcontrol> processer) {
		// TODO ǰ����
		IRule<AggProjectcontrol> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
