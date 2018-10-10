package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLd_mdcontractUnSendApproveBP {

	public AggMdcontractVO[] unSend(AggMdcontractVO[] clientBills,
			AggMdcontractVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggMdcontractVO> update = new BillUpdate<AggMdcontractVO>();
		AggMdcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggMdcontractVO[] clientBills) {
		for (AggMdcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
