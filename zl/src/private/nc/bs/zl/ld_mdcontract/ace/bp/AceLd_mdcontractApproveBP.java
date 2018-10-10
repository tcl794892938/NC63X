package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;

/**
 * 标准单据审核的BP
 */
public class AceLd_mdcontractApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggMdcontractVO[] approve(AggMdcontractVO[] clientBills,
			AggMdcontractVO[] originBills) {
		for (AggMdcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggMdcontractVO> update = new BillUpdate<AggMdcontractVO>();
		AggMdcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
