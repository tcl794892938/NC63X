package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLyw_confirmationUnApproveBP {

	public AggConfirmationVO[] unApprove(AggConfirmationVO[] clientBills,
			AggConfirmationVO[] originBills) {
		for (AggConfirmationVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggConfirmationVO> update = new BillUpdate<AggConfirmationVO>();
		AggConfirmationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
