package nc.bs.zl.cwf_gather.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCwf_gatherUnApproveBP {

	public AggGatherVO[] unApprove(AggGatherVO[] clientBills,
			AggGatherVO[] originBills) {
		for (AggGatherVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggGatherVO> update = new BillUpdate<AggGatherVO>();
		AggGatherVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
