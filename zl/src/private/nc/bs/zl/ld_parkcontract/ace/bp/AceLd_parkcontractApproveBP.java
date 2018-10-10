package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;

/**
 * 标准单据审核的BP
 */
public class AceLd_parkcontractApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggParkcontractVO[] approve(AggParkcontractVO[] clientBills,
			AggParkcontractVO[] originBills) {
		for (AggParkcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggParkcontractVO> update = new BillUpdate<AggParkcontractVO>();
		AggParkcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
