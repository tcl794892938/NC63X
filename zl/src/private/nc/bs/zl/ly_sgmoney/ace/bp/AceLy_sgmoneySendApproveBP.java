package nc.bs.zl.ly_sgmoney.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLy_sgmoneySendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggSgMoneyVO[] sendApprove(AggSgMoneyVO[] clientBills,
			AggSgMoneyVO[] originBills) {
		for (AggSgMoneyVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggSgMoneyVO[] returnVos = new BillUpdate<AggSgMoneyVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
