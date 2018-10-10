package nc.bs.zl.tcl_contract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceTcl_contractUnApproveBP {

	public AggContractVO[] unApprove(AggContractVO[] clientBills,
			AggContractVO[] originBills) {
		for (AggContractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggContractVO> update = new BillUpdate<AggContractVO>();
		AggContractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
