package nc.bs.zl.hql_throwalease.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;

/**
 * 标准单据审核的BP
 */
public class AceHql_throwaleaseApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggThrowaleaseVO[] approve(AggThrowaleaseVO[] clientBills,
			AggThrowaleaseVO[] originBills) {
		for (AggThrowaleaseVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggThrowaleaseVO> update = new BillUpdate<AggThrowaleaseVO>();
		AggThrowaleaseVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
