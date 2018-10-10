package nc.bs.zl.ld_kpregister.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLd_kpregisterUnSendApproveBP {

	public AggKpregisterVO[] unSend(AggKpregisterVO[] clientBills,
			AggKpregisterVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggKpregisterVO> update = new BillUpdate<AggKpregisterVO>();
		AggKpregisterVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggKpregisterVO[] clientBills) {
		for (AggKpregisterVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
