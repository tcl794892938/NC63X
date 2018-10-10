package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLd_mdcontractUnApproveBP {

	public AggMdcontractVO[] unApprove(AggMdcontractVO[] clientBills,
			AggMdcontractVO[] originBills) {
		for (AggMdcontractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggMdcontractVO> update = new BillUpdate<AggMdcontractVO>();
		AggMdcontractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
