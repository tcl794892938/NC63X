package nc.bs.zl.ld_upcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;

/**
 * 标准单据审核的BP
 */
public class AceLd_upcontractApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggUpcontractVO[] approve(AggUpcontractVO[] clientBills,
			AggUpcontractVO[] originBills) {
		for (AggUpcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggUpcontractVO> update = new BillUpdate<AggUpcontractVO>();
		AggUpcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
