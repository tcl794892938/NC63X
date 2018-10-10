package nc.impl.pub.ace;

import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentInsertBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUpdateBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentDeleteBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentSendApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUnSendApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_paymentPubServiceImpl {
	// ����
	public AggPaymentVO[] pubinsertBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggPaymentVO> transferTool = new BillTransferTool<AggPaymentVO>(
					clientFullVOs);
			// ����BP
			AceHql_paymentInsertBP action = new AceHql_paymentInsertBP();
			AggPaymentVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHql_paymentDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggPaymentVO[] pubupdateBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggPaymentVO> transferTool = new BillTransferTool<AggPaymentVO>(
					clientFullVOs);
			AceHql_paymentUpdateBP bp = new AceHql_paymentUpdateBP();
			AggPaymentVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPaymentVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPaymentVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPaymentVO> query = new BillLazyQuery<AggPaymentVO>(
					AggPaymentVO.class);
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
	public AggPaymentVO[] pubsendapprovebills(
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills)
			throws BusinessException {
		AceHql_paymentSendApproveBP bp = new AceHql_paymentSendApproveBP();
		AggPaymentVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggPaymentVO[] pubunsendapprovebills(
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills)
			throws BusinessException {
		AceHql_paymentUnSendApproveBP bp = new AceHql_paymentUnSendApproveBP();
		AggPaymentVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggPaymentVO[] pubapprovebills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_paymentApproveBP bp = new AceHql_paymentApproveBP();
		AggPaymentVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggPaymentVO[] pubunapprovebills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_paymentUnApproveBP bp = new AceHql_paymentUnApproveBP();
		AggPaymentVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}