package nc.bs.zl.hql_throwalease.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHql_throwaleaseSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggThrowaleaseVO[] sendApprove(AggThrowaleaseVO[] clientBills,
			AggThrowaleaseVO[] originBills) {
		for (AggThrowaleaseVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggThrowaleaseVO[] returnVos = new BillUpdate<AggThrowaleaseVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
