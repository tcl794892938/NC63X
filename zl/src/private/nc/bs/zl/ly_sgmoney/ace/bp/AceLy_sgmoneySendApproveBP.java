package nc.bs.zl.ly_sgmoney.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceLy_sgmoneySendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggSgMoneyVO[] sendApprove(AggSgMoneyVO[] clientBills,
			AggSgMoneyVO[] originBills) {
		for (AggSgMoneyVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggSgMoneyVO[] returnVos = new BillUpdate<AggSgMoneyVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
