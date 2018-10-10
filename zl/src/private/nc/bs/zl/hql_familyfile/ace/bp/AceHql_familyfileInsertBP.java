package nc.bs.zl.hql_familyfile.ace.bp;

import nc.bs.zl.hql_familyfile.plugin.bpplugin.Hql_familyfilePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

/**
 * ��׼��������BP
 */
public class AceHql_familyfileInsertBP {

	public AggFamilyfileVO[] insert(AggFamilyfileVO[] bills) {

		InsertBPTemplate<AggFamilyfileVO> bp = new InsertBPTemplate<AggFamilyfileVO>(
				Hql_familyfilePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggFamilyfileVO> processor) {
		// TODO ���������
		IRule<AggFamilyfileVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggFamilyfileVO> processer) {
		// TODO ����ǰ����
		IRule<AggFamilyfileVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
