package nc.bs.zl.hql_familyfile.ace.bp;

import nc.bs.zl.hql_familyfile.plugin.bpplugin.Hql_familyfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

/**
 * �޸ı����BP
 * 
 */
public class AceHql_familyfileUpdateBP {

	public AggFamilyfileVO[] update(AggFamilyfileVO[] bills,
			AggFamilyfileVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggFamilyfileVO> bp = new UpdateBPTemplate<AggFamilyfileVO>(
				Hql_familyfilePluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggFamilyfileVO> processer) {
		// TODO �����
		IRule<AggFamilyfileVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggFamilyfileVO> processer) {
		// TODO ǰ����
		IRule<AggFamilyfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
