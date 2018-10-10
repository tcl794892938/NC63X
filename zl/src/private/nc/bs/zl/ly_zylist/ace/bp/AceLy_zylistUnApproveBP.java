package nc.bs.zl.ly_zylist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLy_zylistUnApproveBP {

	public AggZylistVO[] unApprove(AggZylistVO[] clientBills,
			AggZylistVO[] originBills) {
		for (AggZylistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggZylistVO> update = new BillUpdate<AggZylistVO>();
		AggZylistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
