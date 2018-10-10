package nc.bs.zl.ld_kpregister.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLd_kpregisterSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggKpregisterVO[] sendApprove(AggKpregisterVO[] clientBills,
			AggKpregisterVO[] originBills) {
		for (AggKpregisterVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggKpregisterVO[] returnVos = new BillUpdate<AggKpregisterVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
