package nc.bs.zl.ly_hflist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceLy_hflistUnSendApproveBP {

	public AggHflistVO[] unSend(AggHflistVO[] clientBills,
			AggHflistVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggHflistVO> update = new BillUpdate<AggHflistVO>();
		AggHflistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggHflistVO[] clientBills) {
		for (AggHflistVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
