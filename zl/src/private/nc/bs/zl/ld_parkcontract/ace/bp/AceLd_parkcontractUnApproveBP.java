package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLd_parkcontractUnApproveBP {

	public AggParkcontractVO[] unApprove(AggParkcontractVO[] clientBills,
			AggParkcontractVO[] originBills) {
		for (AggParkcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggParkcontractVO> update = new BillUpdate<AggParkcontractVO>();
		AggParkcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
