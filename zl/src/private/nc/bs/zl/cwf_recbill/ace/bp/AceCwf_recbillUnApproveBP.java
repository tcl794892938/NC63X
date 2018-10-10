package nc.bs.zl.cwf_recbill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCwf_recbillUnApproveBP {

	public AggRecbillVO[] unApprove(AggRecbillVO[] clientBills,
			AggRecbillVO[] originBills) {
		for (AggRecbillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggRecbillVO> update = new BillUpdate<AggRecbillVO>();
		AggRecbillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
