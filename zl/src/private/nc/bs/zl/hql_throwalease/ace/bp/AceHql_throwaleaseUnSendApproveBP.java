package nc.bs.zl.hql_throwalease.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHql_throwaleaseUnSendApproveBP {

	public AggThrowaleaseVO[] unSend(AggThrowaleaseVO[] clientBills,
			AggThrowaleaseVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggThrowaleaseVO> update = new BillUpdate<AggThrowaleaseVO>();
		AggThrowaleaseVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggThrowaleaseVO[] clientBills) {
		for (AggThrowaleaseVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
