package nc.bs.zl.tcl_contract.ace.bp;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.zl.tcl_contract.AggContractVO;

/**
 * �޸ı����BP
 * 
 */
public class AceTcl_contractUpdateBP {

	public AggContractVO[] update(AggContractVO[] bills,
			AggContractVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggContractVO> bp = new UpdateBPTemplate<AggContractVO>(
				Tcl_contractPluginPoint.UPDATE);
		
		String rulecode=bills[0].getParentVO().getVbilltypecode();
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser(),rulecode);
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser(),rulecode);
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggContractVO> processer,String code) {
		// TODO �����
		IRule<AggContractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype(code);
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<AggContractVO> processer,String code) {
		// TODO ǰ����
		IRule<AggContractVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggContractVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype(code);
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
