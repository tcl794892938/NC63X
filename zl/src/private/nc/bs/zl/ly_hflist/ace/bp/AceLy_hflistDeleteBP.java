package nc.bs.zl.ly_hflist.ace.bp;

import nc.bs.zl.ly_hflist.plugin.bpplugin.Ly_hflistPluginPoint;
import nc.vo.zl.ly_hflist.AggHflistVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLy_hflistDeleteBP {

	public void delete(AggHflistVO[] bills) {

		DeleteBPTemplate<AggHflistVO> bp = new DeleteBPTemplate<AggHflistVO>(
				Ly_hflistPluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	/*private void addBeforeRule(AroundProcesser<AggHflistVO> processer) {
		// TODO ǰ����
		IRule<AggHflistVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}*/

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggHflistVO> processer) {
		// TODO �����

	}
}
