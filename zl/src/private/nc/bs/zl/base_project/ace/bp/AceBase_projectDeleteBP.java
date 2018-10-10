package nc.bs.zl.base_project.ace.bp;

import nc.bs.zl.base_project.plugin.bpplugin.Base_projectPluginPoint;
import nc.vo.zl.base_project.AggProjectVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceBase_projectDeleteBP {

	public void delete(AggProjectVO[] bills) {

		DeleteBPTemplate<AggProjectVO> bp = new DeleteBPTemplate<AggProjectVO>(
				Base_projectPluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggProjectVO> processer) {
//		// TODO ǰ����
//		IRule<AggProjectVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggProjectVO> processer) {
		// TODO �����

	}
}
