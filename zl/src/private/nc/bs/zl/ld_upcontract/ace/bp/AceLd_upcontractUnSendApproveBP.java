package nc.bs.zl.ld_upcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLd_upcontractUnSendApproveBP {

	public AggUpcontractVO[] unSend(AggUpcontractVO[] clientBills,
			AggUpcontractVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggUpcontractVO> update = new BillUpdate<AggUpcontractVO>();
		AggUpcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggUpcontractVO[] clientBills) {
		for (AggUpcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
