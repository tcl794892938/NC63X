package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;

/**
 * 标准单据审核的BP
 */
public class AceLyw_confirmationApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggConfirmationVO[] approve(AggConfirmationVO[] clientBills,
			AggConfirmationVO[] originBills) {
		for (AggConfirmationVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggConfirmationVO> update = new BillUpdate<AggConfirmationVO>();
		AggConfirmationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
