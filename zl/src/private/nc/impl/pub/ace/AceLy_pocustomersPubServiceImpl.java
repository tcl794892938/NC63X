package nc.impl.pub.ace;

import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersInsertBP;
import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersUpdateBP;
import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

public abstract class AceLy_pocustomersPubServiceImpl {
	// ����
	public AggPocustomersVO[] pubinsertBills(AggPocustomersVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggPocustomersVO> transferTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			AggPocustomersVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceLy_pocustomersInsertBP action = new AceLy_pocustomersInsertBP();
			AggPocustomersVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggPocustomersVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggPocustomersVO> transferTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			AggPocustomersVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLy_pocustomersDeleteBP deleteBP = new AceLy_pocustomersDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggPocustomersVO[] pubupdateBills(AggPocustomersVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggPocustomersVO> transTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			// ��ȫǰ̨VO
			AggPocustomersVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggPocustomersVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceLy_pocustomersUpdateBP bp = new AceLy_pocustomersUpdateBP();
			AggPocustomersVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPocustomersVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPocustomersVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPocustomersVO> query = new BillLazyQuery<AggPocustomersVO>(
					AggPocustomersVO.class);
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