package nc.bs.zl.ly_carfiles.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceLy_carfilesUnSendApproveBP {

	public AggCarFilesVO[] unSend(AggCarFilesVO[] clientBills,
			AggCarFilesVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggCarFilesVO> update = new BillUpdate<AggCarFilesVO>();
		AggCarFilesVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggCarFilesVO[] clientBills) {
		for (AggCarFilesVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
