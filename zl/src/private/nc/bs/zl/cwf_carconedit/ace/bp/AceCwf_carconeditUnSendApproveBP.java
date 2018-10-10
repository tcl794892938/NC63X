package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceCwf_carconeditUnSendApproveBP {

	public AggCarconeditVO[] unSend(AggCarconeditVO[] clientBills,
			AggCarconeditVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggCarconeditVO> update = new BillUpdate<AggCarconeditVO>();
		AggCarconeditVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggCarconeditVO[] clientBills) {
		for (AggCarconeditVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
