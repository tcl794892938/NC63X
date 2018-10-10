package nc.bs.zl.ld_kpregister.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLd_kpregisterUnApproveBP {

	public AggKpregisterVO[] unApprove(AggKpregisterVO[] clientBills,
			AggKpregisterVO[] originBills) {
		for (AggKpregisterVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggKpregisterVO> update = new BillUpdate<AggKpregisterVO>();
		AggKpregisterVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
