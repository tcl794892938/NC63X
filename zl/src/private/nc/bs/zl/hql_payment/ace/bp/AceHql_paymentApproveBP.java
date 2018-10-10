package nc.bs.zl.hql_payment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.hql_payment.AggPaymentVO;

/**
 * ��׼������˵�BP
 */
public class AceHql_paymentApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggPaymentVO[] approve(AggPaymentVO[] clientBills,
			AggPaymentVO[] originBills) {
		for (AggPaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggPaymentVO> update = new BillUpdate<AggPaymentVO>();
		AggPaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
