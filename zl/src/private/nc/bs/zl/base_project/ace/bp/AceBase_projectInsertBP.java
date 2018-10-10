package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.zl.base_project.AggProjectVO;

/**
 * ��׼��������BP
 */
public class AceBase_projectInsertBP {

	public AggProjectVO[] insert(AggProjectVO[] bills) {

		InsertBPTemplate<AggProjectVO> bp = new InsertBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggProjectVO> processor) {
		// TODO ���������
		IRule<AggProjectVO> rule = null;
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggProjectVO> processer) {
		// TODO ����ǰ����
		IRule<AggProjectVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
