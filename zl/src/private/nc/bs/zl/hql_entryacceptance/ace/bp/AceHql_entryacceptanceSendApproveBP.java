package nc.bs.zl.hql_entryacceptance.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHql_entryacceptanceSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggEntryacceptanceVO[] sendApprove(AggEntryacceptanceVO[] clientBills,
			AggEntryacceptanceVO[] originBills) {
		for (AggEntryacceptanceVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggEntryacceptanceVO[] returnVos = new BillUpdate<AggEntryacceptanceVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
