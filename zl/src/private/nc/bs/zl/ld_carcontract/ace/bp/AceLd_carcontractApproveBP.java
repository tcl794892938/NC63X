package nc.bs.zl.ld_carcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;

/**
 * 标准单据审核的BP
 */
public class AceLd_carcontractApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggCarcontractVO[] approve(AggCarcontractVO[] clientBills,
			AggCarcontractVO[] originBills) {
		for (AggCarcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCarcontractVO> update = new BillUpdate<AggCarcontractVO>();
		AggCarcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
