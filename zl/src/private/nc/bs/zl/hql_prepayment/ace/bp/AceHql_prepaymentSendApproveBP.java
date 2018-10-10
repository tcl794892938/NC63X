package nc.bs.zl.hql_prepayment.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHql_prepaymentSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggPrepaymentVO[] sendApprove(AggPrepaymentVO[] clientBills,
			AggPrepaymentVO[] originBills) {
		for (AggPrepaymentVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggPrepaymentVO[] returnVos = new BillUpdate<AggPrepaymentVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
