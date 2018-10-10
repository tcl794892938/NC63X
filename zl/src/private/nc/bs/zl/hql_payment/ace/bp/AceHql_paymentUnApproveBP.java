package nc.bs.zl.hql_payment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHql_paymentUnApproveBP {

	public AggPaymentVO[] unApprove(AggPaymentVO[] clientBills,
			AggPaymentVO[] originBills) {
		for (AggPaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggPaymentVO> update = new BillUpdate<AggPaymentVO>();
		AggPaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
