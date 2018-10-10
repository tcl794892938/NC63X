package nc.bs.zl.ly_bslist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ly_bslist.AggBslistVO;

/**
 * 标准单据审核的BP
 */
public class AceLy_bslistApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBslistVO[] approve(AggBslistVO[] clientBills,
			AggBslistVO[] originBills) {
		for (AggBslistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBslistVO> update = new BillUpdate<AggBslistVO>();
		AggBslistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
