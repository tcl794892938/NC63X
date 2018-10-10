package nc.bs.zl.ly_sgmoney.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceLy_sgmoneyUnApproveBP {

	public AggSgMoneyVO[] unApprove(AggSgMoneyVO[] clientBills,
			AggSgMoneyVO[] originBills) {
		for (AggSgMoneyVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSgMoneyVO> update = new BillUpdate<AggSgMoneyVO>();
		AggSgMoneyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
