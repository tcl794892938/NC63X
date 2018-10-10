package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceCwf_carconeditSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggCarconeditVO[] sendApprove(AggCarconeditVO[] clientBills,
			AggCarconeditVO[] originBills) {
		for (AggCarconeditVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggCarconeditVO[] returnVos = new BillUpdate<AggCarconeditVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
