package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLd_parkcontractSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggParkcontractVO[] sendApprove(AggParkcontractVO[] clientBills,
			AggParkcontractVO[] originBills) {
		for (AggParkcontractVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggParkcontractVO[] returnVos = new BillUpdate<AggParkcontractVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
