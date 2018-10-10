package nc.bs.zl.hql_prepayment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;

/**
 * 标准单据审核的BP
 */
public class AceHql_prepaymentApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggPrepaymentVO[] approve(AggPrepaymentVO[] clientBills,
			AggPrepaymentVO[] originBills) {
		for (AggPrepaymentVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggPrepaymentVO> update = new BillUpdate<AggPrepaymentVO>();
		AggPrepaymentVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
