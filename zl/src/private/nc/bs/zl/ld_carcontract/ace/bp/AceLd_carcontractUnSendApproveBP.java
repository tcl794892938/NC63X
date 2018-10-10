package nc.bs.zl.ld_carcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLd_carcontractUnSendApproveBP {

	public AggCarcontractVO[] unSend(AggCarcontractVO[] clientBills,
			AggCarcontractVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggCarcontractVO> update = new BillUpdate<AggCarcontractVO>();
		AggCarcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggCarcontractVO[] clientBills) {
		for (AggCarcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
