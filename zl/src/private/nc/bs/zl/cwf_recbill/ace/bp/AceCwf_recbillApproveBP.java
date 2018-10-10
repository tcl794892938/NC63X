package nc.bs.zl.cwf_recbill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.cwf_recbill.AggRecbillVO;

/**
 * 标准单据审核的BP
 */
public class AceCwf_recbillApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggRecbillVO[] approve(AggRecbillVO[] clientBills,
			AggRecbillVO[] originBills) {
		for (AggRecbillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggRecbillVO> update = new BillUpdate<AggRecbillVO>();
		AggRecbillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
