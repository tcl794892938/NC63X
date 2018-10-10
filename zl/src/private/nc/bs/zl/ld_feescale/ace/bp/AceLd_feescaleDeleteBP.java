package nc.bs.zl.ld_feescale.ace.bp;

import nc.bs.zl.ld_feescale.plugin.bpplugin.Ld_feescalePluginPoint;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_feescaleDeleteBP {

	public void delete(AggFeescaleVO[] bills) {

		DeleteBPTemplate<AggFeescaleVO> bp = new DeleteBPTemplate<AggFeescaleVO>(
				Ld_feescalePluginPoint.DELETE);
		// ����ִ��ǰ����
		//this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

//	private void addBeforeRule(AroundProcesser<AggFeescaleVO> processer) {
//		// TODO ǰ����
//		IRule<AggFeescaleVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
//		processer.addBeforeRule(rule);
//	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggFeescaleVO> processer) {
		// TODO �����

	}
}
