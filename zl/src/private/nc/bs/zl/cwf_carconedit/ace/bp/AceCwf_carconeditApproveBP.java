package nc.bs.zl.cwf_carconedit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;

/**
 * 标准单据审核的BP
 */
public class AceCwf_carconeditApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggCarconeditVO[] approve(AggCarconeditVO[] clientBills,
			AggCarconeditVO[] originBills) {
		for (AggCarconeditVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCarconeditVO> update = new BillUpdate<AggCarconeditVO>();
		AggCarconeditVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
