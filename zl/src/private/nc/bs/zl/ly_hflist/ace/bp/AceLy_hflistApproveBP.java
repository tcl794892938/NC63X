package nc.bs.zl.ly_hflist.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.zl.ly_hflist.AggHflistVO;

/**
 * ��׼������˵�BP
 */
public class AceLy_hflistApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggHflistVO[] approve(AggHflistVO[] clientBills,
			AggHflistVO[] originBills) {
		for (AggHflistVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggHflistVO> update = new BillUpdate<AggHflistVO>();
		AggHflistVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
