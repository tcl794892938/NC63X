package nc.bs.zl.ly_sgmoney.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;

/**
 * 标准单据审核的BP
 */
public class AceLy_sgmoneyApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggSgMoneyVO[] approve(AggSgMoneyVO[] clientBills,
			AggSgMoneyVO[] originBills) {
		for (AggSgMoneyVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggSgMoneyVO> update = new BillUpdate<AggSgMoneyVO>();
		AggSgMoneyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
