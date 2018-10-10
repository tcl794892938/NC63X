package nc.bs.zl.tcl_contract.ace.bp;

import nc.bs.zl.tcl_contract.plugin.bpplugin.Tcl_contractPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.zl.tcl_contract.AggContractVO;

/**
 * 修改保存的BP
 * 
 */
public class AceTcl_contractUpdateBP {

	public AggContractVO[] update(AggContractVO[] bills,
			AggContractVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggContractVO> bp = new UpdateBPTemplate<AggContractVO>(
				Tcl_contractPluginPoint.UPDATE);
		
		String rulecode=bills[0].getParentVO().getVbilltypecode();
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser(),rulecode);
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser(),rulecode);
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggContractVO> processer,String code) {
		// TODO 后规则
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
		// TODO 前规则
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
