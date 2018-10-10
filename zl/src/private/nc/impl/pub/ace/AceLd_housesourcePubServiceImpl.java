package nc.impl.pub.ace;

import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceInsertBP;
import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceUpdateBP;
import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

public abstract class AceLd_housesourcePubServiceImpl {
	// ����
	public AggHousesourceVO[] pubinsertBills(AggHousesourceVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggHousesourceVO> transferTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			AggHousesourceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceLd_housesourceInsertBP action = new AceLd_housesourceInsertBP();
			AggHousesourceVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggHousesourceVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggHousesourceVO> transferTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			AggHousesourceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLd_housesourceDeleteBP deleteBP = new AceLd_housesourceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggHousesourceVO[] pubupdateBills(AggHousesourceVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggHousesourceVO> transTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			// ��ȫǰ̨VO
			AggHousesourceVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggHousesourceVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceLd_housesourceUpdateBP bp = new AceLd_housesourceUpdateBP();
			AggHousesourceVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggHousesourceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggHousesourceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggHousesourceVO> query = new BillLazyQuery<AggHousesourceVO>(
					AggHousesourceVO.class);
			bills = query.query(queryScheme, " order by to_number(floorn) desc,to_number(unit) asc,to_number(roomnumber) asc");
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

}