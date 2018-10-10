package nc.impl.pub.ace;

import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceInsertBP;
import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceUpdateBP;
import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

public abstract class AceLy_businesssourcePubServiceImpl {
	// ����
	public AggBusinessSourceVO[] pubinsertBills(AggBusinessSourceVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBusinessSourceVO> transferTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			AggBusinessSourceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceLy_businesssourceInsertBP action = new AceLy_businesssourceInsertBP();
			AggBusinessSourceVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBusinessSourceVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggBusinessSourceVO> transferTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			AggBusinessSourceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLy_businesssourceDeleteBP deleteBP = new AceLy_businesssourceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBusinessSourceVO[] pubupdateBills(AggBusinessSourceVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBusinessSourceVO> transTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			// ��ȫǰ̨VO
			AggBusinessSourceVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggBusinessSourceVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceLy_businesssourceUpdateBP bp = new AceLy_businesssourceUpdateBP();
			AggBusinessSourceVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBusinessSourceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBusinessSourceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBusinessSourceVO> query = new BillLazyQuery<AggBusinessSourceVO>(
					AggBusinessSourceVO.class);
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