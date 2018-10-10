package nc.bs.zl.ld_upcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLd_upcontractSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggUpcontractVO[] sendApprove(AggUpcontractVO[] clientBills,
			AggUpcontractVO[] originBills) {
		for (AggUpcontractVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggUpcontractVO[] returnVos = new BillUpdate<AggUpcontractVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
