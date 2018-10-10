package nc.bs.zl.lyw_billconfirmed.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;

/**
 * ��׼������˵�BP
 */
public class AceLyw_billconfirmedApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggBillconfirmedVO[] approve(AggBillconfirmedVO[] clientBills,
			AggBillconfirmedVO[] originBills) {
		for (AggBillconfirmedVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBillconfirmedVO> update = new BillUpdate<AggBillconfirmedVO>();
		AggBillconfirmedVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
