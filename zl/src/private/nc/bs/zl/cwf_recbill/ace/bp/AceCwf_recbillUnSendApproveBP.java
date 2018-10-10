package nc.bs.zl.cwf_recbill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceCwf_recbillUnSendApproveBP {

	public AggRecbillVO[] unSend(AggRecbillVO[] clientBills,
			AggRecbillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggRecbillVO> update = new BillUpdate<AggRecbillVO>();
		AggRecbillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggRecbillVO[] clientBills) {
		for (AggRecbillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
