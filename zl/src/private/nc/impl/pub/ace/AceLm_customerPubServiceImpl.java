package nc.impl.pub.ace;

import nc.bs.zl.lm_customer.ace.bp.AceLm_customerInsertBP;
import nc.bs.zl.lm_customer.ace.bp.AceLm_customerUpdateBP;
import nc.bs.zl.lm_customer.ace.bp.AceLm_customerDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.lm_customer.AggCustomerVO;

public abstract class AceLm_customerPubServiceImpl {
	// ����
	public AggCustomerVO[] pubinsertBills(AggCustomerVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggCustomerVO> transferTool = new BillTransferTool<AggCustomerVO>(
					vos);
			AggCustomerVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceLm_customerInsertBP action = new AceLm_customerInsertBP();
			AggCustomerVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggCustomerVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggCustomerVO> transferTool = new BillTransferTool<AggCustomerVO>(
					vos);
			AggCustomerVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLm_customerDeleteBP deleteBP = new AceLm_customerDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggCustomerVO[] pubupdateBills(AggCustomerVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggCustomerVO> transTool = new BillTransferTool<AggCustomerVO>(
					vos);
			// ��ȫǰ̨VO
			AggCustomerVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggCustomerVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceLm_customerUpdateBP bp = new AceLm_customerUpdateBP();
			AggCustomerVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCustomerVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCustomerVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCustomerVO> query = new BillLazyQuery<AggCustomerVO>(
					AggCustomerVO.class);
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