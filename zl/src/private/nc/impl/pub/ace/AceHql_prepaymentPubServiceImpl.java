package nc.impl.pub.ace;

import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentInsertBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUpdateBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentDeleteBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentSendApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUnSendApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_prepaymentPubServiceImpl {
	// ����
	public AggPrepaymentVO[] pubinsertBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggPrepaymentVO> transferTool = new BillTransferTool<AggPrepaymentVO>(
					clientFullVOs);
			// ����BP
			AceHql_prepaymentInsertBP action = new AceHql_prepaymentInsertBP();
			AggPrepaymentVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHql_prepaymentDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggPrepaymentVO[] pubupdateBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggPrepaymentVO> transferTool = new BillTransferTool<AggPrepaymentVO>(
					clientFullVOs);
			AceHql_prepaymentUpdateBP bp = new AceHql_prepaymentUpdateBP();
			AggPrepaymentVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPrepaymentVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPrepaymentVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPrepaymentVO> query = new BillLazyQuery<AggPrepaymentVO>(
					AggPrepaymentVO.class);
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

	// �ύ
	public AggPrepaymentVO[] pubsendapprovebills(
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills)
			throws BusinessException {
		AceHql_prepaymentSendApproveBP bp = new AceHql_prepaymentSendApproveBP();
		AggPrepaymentVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggPrepaymentVO[] pubunsendapprovebills(
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills)
			throws BusinessException {
		AceHql_prepaymentUnSendApproveBP bp = new AceHql_prepaymentUnSendApproveBP();
		AggPrepaymentVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggPrepaymentVO[] pubapprovebills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_prepaymentApproveBP bp = new AceHql_prepaymentApproveBP();
		AggPrepaymentVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggPrepaymentVO[] pubunapprovebills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_prepaymentUnApproveBP bp = new AceHql_prepaymentUnApproveBP();
		AggPrepaymentVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}