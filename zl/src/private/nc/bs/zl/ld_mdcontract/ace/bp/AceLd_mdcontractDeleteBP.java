package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.bs.zl.ld_mdcontract.plugin.bpplugin.Ld_mdcontractPluginPoint;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceLd_mdcontractDeleteBP {

	public void delete(AggMdcontractVO[] bills) {

		DeleteBPTemplate<AggMdcontractVO> bp = new DeleteBPTemplate<AggMdcontractVO>(
				Ld_mdcontractPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggMdcontractVO> processer) {
		// TODO ǰ����
		IRule<AggMdcontractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggMdcontractVO> processer) {
		// TODO �����

	}
}
