package nc.bs.zl.ld_housesource.ace.bp;

import nc.bs.zl.ld_housesource.plugin.bpplugin.Ld_housesourcePluginPoint;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_housesourceDeleteBP {

	public void delete(AggHousesourceVO[] bills) {

		DeleteBPTemplate<AggHousesourceVO> bp = new DeleteBPTemplate<AggHousesourceVO>(
				Ld_housesourcePluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggHousesourceVO> processer) {
//		// TODO ǰ����
//		IRule<AggHousesourceVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggHousesourceVO> processer) {
		// TODO �����

	}
}
