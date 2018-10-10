package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceLyw_billconfirmedSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggBillconfirmedVO[] sendApprove(AggBillconfirmedVO[] clientBills,
			AggBillconfirmedVO[] originBills) {
		for (AggBillconfirmedVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggBillconfirmedVO[] returnVos = new BillUpdate<AggBillconfirmedVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
