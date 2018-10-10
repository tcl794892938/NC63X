package nc.bs.zl.cwf_gather.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.cwf_gather.AggGatherVO;

/**
 * 标准单据审核的BP
 */
public class AceCwf_gatherApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggGatherVO[] approve(AggGatherVO[] clientBills,
			AggGatherVO[] originBills) {
		for (AggGatherVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggGatherVO> update = new BillUpdate<AggGatherVO>();
		AggGatherVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
