package nc.bs.zl.ly_hflist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLy_hflistUnApproveBP {

	public AggHflistVO[] unApprove(AggHflistVO[] clientBills,
			AggHflistVO[] originBills) {
		for (AggHflistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggHflistVO> update = new BillUpdate<AggHflistVO>();
		AggHflistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
