package nc.bs.zl.ly_bslist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLy_bslistUnApproveBP {

	public AggBslistVO[] unApprove(AggBslistVO[] clientBills,
			AggBslistVO[] originBills) {
		for (AggBslistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBslistVO> update = new BillUpdate<AggBslistVO>();
		AggBslistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
