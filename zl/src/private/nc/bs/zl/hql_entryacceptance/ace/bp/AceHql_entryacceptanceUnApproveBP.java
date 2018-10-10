package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHql_entryacceptanceUnApproveBP {

	public AggEntryacceptanceVO[] unApprove(AggEntryacceptanceVO[] clientBills,
			AggEntryacceptanceVO[] originBills) {
		for (AggEntryacceptanceVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggEntryacceptanceVO> update = new BillUpdate<AggEntryacceptanceVO>();
		AggEntryacceptanceVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
