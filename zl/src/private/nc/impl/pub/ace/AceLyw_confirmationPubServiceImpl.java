package nc.impl.pub.ace;

import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationInsertBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUpdateBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationDeleteBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationSendApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUnSendApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLyw_confirmationPubServiceImpl {
	// ����
	public AggConfirmationVO[] pubinsertBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggConfirmationVO> transferTool = new BillTransferTool<AggConfirmationVO>(
					clientFullVOs);
			// ����BP
			AceLyw_confirmationInsertBP action = new AceLyw_confirmationInsertBP();
			AggConfirmationVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLyw_confirmationDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggConfirmationVO[] pubupdateBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggConfirmationVO> transferTool = new BillTransferTool<AggConfirmationVO>(
					clientFullVOs);
			AceLyw_confirmationUpdateBP bp = new AceLyw_confirmationUpdateBP();
			AggConfirmationVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggConfirmationVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggConfirmationVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggConfirmationVO> query = new BillLazyQuery<AggConfirmationVO>(
					AggConfirmationVO.class);
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
	public AggConfirmationVO[] pubsendapprovebills(
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills)
			throws BusinessException {
		AceLyw_confirmationSendApproveBP bp = new AceLyw_confirmationSendApproveBP();
		AggConfirmationVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggConfirmationVO[] pubunsendapprovebills(
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills)
			throws BusinessException {
		AceLyw_confirmationUnSendApproveBP bp = new AceLyw_confirmationUnSendApproveBP();
		AggConfirmationVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggConfirmationVO[] pubapprovebills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_confirmationApproveBP bp = new AceLyw_confirmationApproveBP();
		AggConfirmationVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggConfirmationVO[] pubunapprovebills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_confirmationUnApproveBP bp = new AceLyw_confirmationUnApproveBP();
		AggConfirmationVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}