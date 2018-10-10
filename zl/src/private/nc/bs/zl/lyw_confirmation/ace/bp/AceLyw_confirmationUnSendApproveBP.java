package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLyw_confirmationUnSendApproveBP {

	public AggConfirmationVO[] unSend(AggConfirmationVO[] clientBills,
			AggConfirmationVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggConfirmationVO> update = new BillUpdate<AggConfirmationVO>();
		AggConfirmationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggConfirmationVO[] clientBills) {
		for (AggConfirmationVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
