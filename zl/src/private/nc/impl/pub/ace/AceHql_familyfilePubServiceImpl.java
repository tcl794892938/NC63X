package nc.impl.pub.ace;

import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileInsertBP;
import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileUpdateBP;
import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

public abstract class AceHql_familyfilePubServiceImpl {
	// ����
	public AggFamilyfileVO[] pubinsertBills(AggFamilyfileVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggFamilyfileVO> transferTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			AggFamilyfileVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceHql_familyfileInsertBP action = new AceHql_familyfileInsertBP();
			AggFamilyfileVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggFamilyfileVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggFamilyfileVO> transferTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			AggFamilyfileVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_familyfileDeleteBP deleteBP = new AceHql_familyfileDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggFamilyfileVO[] pubupdateBills(AggFamilyfileVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggFamilyfileVO> transTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			// ��ȫǰ̨VO
			AggFamilyfileVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggFamilyfileVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceHql_familyfileUpdateBP bp = new AceHql_familyfileUpdateBP();
			AggFamilyfileVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggFamilyfileVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggFamilyfileVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggFamilyfileVO> query = new BillLazyQuery<AggFamilyfileVO>(
					AggFamilyfileVO.class);
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