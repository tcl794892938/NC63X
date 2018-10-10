package nc.bs.zl.ly_zylist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLy_zylistSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggZylistVO[] sendApprove(AggZylistVO[] clientBills,
			AggZylistVO[] originBills) {
		for (AggZylistVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggZylistVO[] returnVos = new BillUpdate<AggZylistVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
