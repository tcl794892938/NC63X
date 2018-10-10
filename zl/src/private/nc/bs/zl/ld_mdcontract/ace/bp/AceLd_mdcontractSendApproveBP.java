package nc.bs.zl.ld_mdcontract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLd_mdcontractSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggMdcontractVO[] sendApprove(AggMdcontractVO[] clientBills,
			AggMdcontractVO[] originBills) {
		for (AggMdcontractVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggMdcontractVO[] returnVos = new BillUpdate<AggMdcontractVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
