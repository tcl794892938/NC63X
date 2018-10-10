package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHql_entryacceptanceUnSendApproveBP {

	public AggEntryacceptanceVO[] unSend(AggEntryacceptanceVO[] clientBills,
			AggEntryacceptanceVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggEntryacceptanceVO> update = new BillUpdate<AggEntryacceptanceVO>();
		AggEntryacceptanceVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggEntryacceptanceVO[] clientBills) {
		for (AggEntryacceptanceVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
