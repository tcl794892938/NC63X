package nc.impl.pub.ace;

import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceInsertBP;
import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceUpdateBP;
import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

public abstract class AceHql_jt_acceptancePubServiceImpl {
	// ����
	public AggJt_acceptanceVO[] pubinsertBills(AggJt_acceptanceVO[] vos)
			throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggJt_acceptanceVO> transferTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			AggJt_acceptanceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// ����BP
			AceHql_jt_acceptanceInsertBP action = new AceHql_jt_acceptanceInsertBP();
			AggJt_acceptanceVO[] retvos = action.insert(mergedVO);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggJt_acceptanceVO[] vos) throws BusinessException {
		try {
			// ���� �Ƚ�ts
			BillTransferTool<AggJt_acceptanceVO> transferTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			AggJt_acceptanceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_jt_acceptanceDeleteBP deleteBP = new AceHql_jt_acceptanceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggJt_acceptanceVO[] pubupdateBills(AggJt_acceptanceVO[] vos)
			throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggJt_acceptanceVO> transTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			// ��ȫǰ̨VO
			AggJt_acceptanceVO[] fullBills = transTool.getClientFullInfoBill();
			// ����޸�ǰvo
			AggJt_acceptanceVO[] originBills = transTool.getOriginBills();
			// ����BP
			AceHql_jt_acceptanceUpdateBP bp = new AceHql_jt_acceptanceUpdateBP();
			AggJt_acceptanceVO[] retBills = bp.update(fullBills, originBills);
			// ���췵������
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggJt_acceptanceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggJt_acceptanceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggJt_acceptanceVO> query = new BillLazyQuery<AggJt_acceptanceVO>(
					AggJt_acceptanceVO.class);
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