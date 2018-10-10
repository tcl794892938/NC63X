package nc.bs.zl.cwf_recbill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceCwf_recbillSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggRecbillVO[] sendApprove(AggRecbillVO[] clientBills,
			AggRecbillVO[] originBills) {
		for (AggRecbillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggRecbillVO[] returnVos = new BillUpdate<AggRecbillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
