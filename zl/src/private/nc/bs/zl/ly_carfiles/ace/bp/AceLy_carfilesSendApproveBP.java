package nc.bs.zl.ly_carfiles.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLy_carfilesSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggCarFilesVO[] sendApprove(AggCarFilesVO[] clientBills,
			AggCarFilesVO[] originBills) {
		for (AggCarFilesVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggCarFilesVO[] returnVos = new BillUpdate<AggCarFilesVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
