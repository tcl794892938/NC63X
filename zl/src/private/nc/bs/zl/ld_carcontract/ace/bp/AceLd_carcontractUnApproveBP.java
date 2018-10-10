package nc.bs.zl.ld_carcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLd_carcontractUnApproveBP {

	public AggCarcontractVO[] unApprove(AggCarcontractVO[] clientBills,
			AggCarcontractVO[] originBills) {
		for (AggCarcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCarcontractVO> update = new BillUpdate<AggCarcontractVO>();
		AggCarcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
