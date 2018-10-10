package nc.bs.zl.hql_payment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHql_paymentUnSendApproveBP {

	public AggPaymentVO[] unSend(AggPaymentVO[] clientBills,
			AggPaymentVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggPaymentVO> update = new BillUpdate<AggPaymentVO>();
		AggPaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggPaymentVO[] clientBills) {
		for (AggPaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
