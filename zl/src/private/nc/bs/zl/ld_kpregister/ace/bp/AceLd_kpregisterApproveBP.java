package nc.bs.zl.ld_kpregister.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;

/**
 * 标准单据审核的BP
 */
public class AceLd_kpregisterApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggKpregisterVO[] approve(AggKpregisterVO[] clientBills,
			AggKpregisterVO[] originBills) {
		for (AggKpregisterVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggKpregisterVO> update = new BillUpdate<AggKpregisterVO>();
		AggKpregisterVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
