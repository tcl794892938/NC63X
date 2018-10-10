package nc.bs.zl.ld_parkcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLd_parkcontractSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggParkcontractVO[] sendApprove(AggParkcontractVO[] clientBills,
			AggParkcontractVO[] originBills) {
		for (AggParkcontractVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggParkcontractVO[] returnVos = new BillUpdate<AggParkcontractVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
