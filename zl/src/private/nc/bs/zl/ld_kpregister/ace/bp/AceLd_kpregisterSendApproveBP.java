package nc.bs.zl.ld_kpregister.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLd_kpregisterSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggKpregisterVO[] sendApprove(AggKpregisterVO[] clientBills,
			AggKpregisterVO[] originBills) {
		for (AggKpregisterVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggKpregisterVO[] returnVos = new BillUpdate<AggKpregisterVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
