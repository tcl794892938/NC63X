package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLyw_billconfirmedSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggBillconfirmedVO[] sendApprove(AggBillconfirmedVO[] clientBills,
			AggBillconfirmedVO[] originBills) {
		for (AggBillconfirmedVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggBillconfirmedVO[] returnVos = new BillUpdate<AggBillconfirmedVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
