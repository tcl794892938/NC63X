package nc.bs.zl.ly_carfiles.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLy_carfilesSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggCarFilesVO[] sendApprove(AggCarFilesVO[] clientBills,
			AggCarFilesVO[] originBills) {
		for (AggCarFilesVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggCarFilesVO[] returnVos = new BillUpdate<AggCarFilesVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
