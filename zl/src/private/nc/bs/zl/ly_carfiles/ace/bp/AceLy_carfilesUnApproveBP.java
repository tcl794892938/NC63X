package nc.bs.zl.ly_carfiles.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceLy_carfilesUnApproveBP {

	public AggCarFilesVO[] unApprove(AggCarFilesVO[] clientBills,
			AggCarFilesVO[] originBills) {
		for (AggCarFilesVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCarFilesVO> update = new BillUpdate<AggCarFilesVO>();
		AggCarFilesVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
