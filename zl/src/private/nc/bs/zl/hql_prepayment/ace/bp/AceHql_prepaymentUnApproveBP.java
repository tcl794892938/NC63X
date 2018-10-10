package nc.bs.zl.hql_prepayment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHql_prepaymentUnApproveBP {

	public AggPrepaymentVO[] unApprove(AggPrepaymentVO[] clientBills,
			AggPrepaymentVO[] originBills) {
		for (AggPrepaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggPrepaymentVO> update = new BillUpdate<AggPrepaymentVO>();
		AggPrepaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
