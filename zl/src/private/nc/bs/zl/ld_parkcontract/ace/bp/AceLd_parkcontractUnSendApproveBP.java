package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLd_parkcontractUnSendApproveBP {

	public AggParkcontractVO[] unSend(AggParkcontractVO[] clientBills,
			AggParkcontractVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggParkcontractVO> update = new BillUpdate<AggParkcontractVO>();
		AggParkcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggParkcontractVO[] clientBills) {
		for (AggParkcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
