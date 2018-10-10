package nc.bs.zl.hql_throwalease.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHql_throwaleaseUnApproveBP {

	public AggThrowaleaseVO[] unApprove(AggThrowaleaseVO[] clientBills,
			AggThrowaleaseVO[] originBills) {
		for (AggThrowaleaseVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggThrowaleaseVO> update = new BillUpdate<AggThrowaleaseVO>();
		AggThrowaleaseVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
