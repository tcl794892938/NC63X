package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;

/**
 * ��׼������˵�BP
 */
public class AceHql_entryacceptanceApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggEntryacceptanceVO[] approve(AggEntryacceptanceVO[] clientBills,
			AggEntryacceptanceVO[] originBills) {
		for (AggEntryacceptanceVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggEntryacceptanceVO> update = new BillUpdate<AggEntryacceptanceVO>();
		AggEntryacceptanceVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
