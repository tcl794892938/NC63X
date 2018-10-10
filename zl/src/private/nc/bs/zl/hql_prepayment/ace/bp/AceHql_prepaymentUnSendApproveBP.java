package nc.bs.zl.hql_prepayment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHql_prepaymentUnSendApproveBP {

	public AggPrepaymentVO[] unSend(AggPrepaymentVO[] clientBills,
			AggPrepaymentVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggPrepaymentVO> update = new BillUpdate<AggPrepaymentVO>();
		AggPrepaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggPrepaymentVO[] clientBills) {
		for (AggPrepaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
