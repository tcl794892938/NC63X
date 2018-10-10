package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLyw_billconfirmedUnApproveBP {

	public AggBillconfirmedVO[] unApprove(AggBillconfirmedVO[] clientBills,
			AggBillconfirmedVO[] originBills) {
		for (AggBillconfirmedVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBillconfirmedVO> update = new BillUpdate<AggBillconfirmedVO>();
		AggBillconfirmedVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
