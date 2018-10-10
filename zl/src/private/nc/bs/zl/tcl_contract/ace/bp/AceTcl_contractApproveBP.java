package nc.bs.zl.tcl_contract.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.tcl_contract.AggContractVO;

/**
 * 标准单据审核的BP
 */
public class AceTcl_contractApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggContractVO[] approve(AggContractVO[] clientBills,
			AggContractVO[] originBills) {
		for (AggContractVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggContractVO> update = new BillUpdate<AggContractVO>();
		AggContractVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
