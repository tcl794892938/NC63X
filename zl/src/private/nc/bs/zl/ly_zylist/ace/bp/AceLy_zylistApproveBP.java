package nc.bs.zl.ly_zylist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ly_zylist.AggZylistVO;

/**
 * 标准单据审核的BP
 */
public class AceLy_zylistApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggZylistVO[] approve(AggZylistVO[] clientBills,
			AggZylistVO[] originBills) {
		for (AggZylistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggZylistVO> update = new BillUpdate<AggZylistVO>();
		AggZylistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
