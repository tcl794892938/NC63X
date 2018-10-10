package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLyw_confirmationSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggConfirmationVO[] sendApprove(AggConfirmationVO[] clientBills,
			AggConfirmationVO[] originBills) {
		for (AggConfirmationVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggConfirmationVO[] returnVos = new BillUpdate<AggConfirmationVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
