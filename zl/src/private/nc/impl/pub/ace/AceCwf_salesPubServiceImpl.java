package nc.impl.pub.ace;

import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesInsertBP;
import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesUpdateBP;
import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_sales.AggSalesVO;

public abstract class AceCwf_salesPubServiceImpl {
	// ����
	public AggSalesVO[] pubinsertBills(AggSalesVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSalesVO> transferTool = new BillTransferTool<AggSalesVO>(
					vos);
			AggSalesVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceCwf_salesInsertBP action = new AceCwf_salesInsertBP();
			AggSalesVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSalesVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggSalesVO> transferTool = new BillTransferTool<AggSalesVO>(
					vos);
			AggSalesVO[] fullBills = transferTool.getClientFullInfoBill();
			AceCwf_salesDeleteBP deleteBP = new AceCwf_salesDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSalesVO[] pubupdateBills(AggSalesVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSalesVO> transTool = new BillTransferTool<AggSalesVO>(
					vos);
			// ��ȫǰ̨VO
			AggSalesVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggSalesVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceCwf_salesUpdateBP bp = new AceCwf_salesUpdateBP();
			AggSalesVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSalesVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSalesVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSalesVO> query = new BillLazyQuery<AggSalesVO>(
					AggSalesVO.class);
			bills = query.query(queryScheme, null);
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