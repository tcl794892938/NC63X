package nc.bs.zl.ld_upcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLd_upcontractUnApproveBP {

	public AggUpcontractVO[] unApprove(AggUpcontractVO[] clientBills,
			AggUpcontractVO[] originBills) {
		for (AggUpcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggUpcontractVO> update = new BillUpdate<AggUpcontractVO>();
		AggUpcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
