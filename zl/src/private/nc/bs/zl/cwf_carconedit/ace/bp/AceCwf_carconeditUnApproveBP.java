package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCwf_carconeditUnApproveBP {

	public AggCarconeditVO[] unApprove(AggCarconeditVO[] clientBills,
			AggCarconeditVO[] originBills) {
		for (AggCarconeditVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCarconeditVO> update = new BillUpdate<AggCarconeditVO>();
		AggCarconeditVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
