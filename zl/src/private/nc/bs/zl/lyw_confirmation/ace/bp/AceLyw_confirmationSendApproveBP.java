package nc.bs.zl.lyw_confirmation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLyw_confirmationSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggConfirmationVO[] sendApprove(AggConfirmationVO[] clientBills,
			AggConfirmationVO[] originBills) {
		for (AggConfirmationVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggConfirmationVO[] returnVos = new BillUpdate<AggConfirmationVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
