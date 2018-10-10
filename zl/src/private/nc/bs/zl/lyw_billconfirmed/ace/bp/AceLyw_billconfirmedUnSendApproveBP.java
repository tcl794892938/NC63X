package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLyw_billconfirmedUnSendApproveBP {

	public AggBillconfirmedVO[] unSend(AggBillconfirmedVO[] clientBills,
			AggBillconfirmedVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggBillconfirmedVO> update = new BillUpdate<AggBillconfirmedVO>();
		AggBillconfirmedVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggBillconfirmedVO[] clientBills) {
		for (AggBillconfirmedVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
