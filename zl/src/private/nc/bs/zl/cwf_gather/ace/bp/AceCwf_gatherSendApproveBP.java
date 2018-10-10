package nc.bs.zl.cwf_gather.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceCwf_gatherSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggGatherVO[] sendApprove(AggGatherVO[] clientBills,
			AggGatherVO[] originBills) {
		for (AggGatherVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggGatherVO[] returnVos = new BillUpdate<AggGatherVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
