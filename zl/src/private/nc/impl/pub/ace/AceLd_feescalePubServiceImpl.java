package nc.impl.pub.ace;

import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleInsertBP;
import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleUpdateBP;
import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

public abstract class AceLd_feescalePubServiceImpl {
	// ����
	public AggFeescaleVO[] pubinsertBills(AggFeescaleVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggFeescaleVO> transferTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			AggFeescaleVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceLd_feescaleInsertBP action = new AceLd_feescaleInsertBP();
			AggFeescaleVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggFeescaleVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggFeescaleVO> transferTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			AggFeescaleVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLd_feescaleDeleteBP deleteBP = new AceLd_feescaleDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggFeescaleVO[] pubupdateBills(AggFeescaleVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggFeescaleVO> transTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			// ��ȫǰ̨VO
			AggFeescaleVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggFeescaleVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceLd_feescaleUpdateBP bp = new AceLd_feescaleUpdateBP();
			AggFeescaleVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggFeescaleVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggFeescaleVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggFeescaleVO> query = new BillLazyQuery<AggFeescaleVO>(
					AggFeescaleVO.class);
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