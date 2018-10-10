package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceCwf_carconeditSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggCarconeditVO[] sendApprove(AggCarconeditVO[] clientBills,
			AggCarconeditVO[] originBills) {
		for (AggCarconeditVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggCarconeditVO[] returnVos = new BillUpdate<AggCarconeditVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
