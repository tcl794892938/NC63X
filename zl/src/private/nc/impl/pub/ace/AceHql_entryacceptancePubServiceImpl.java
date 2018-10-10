package nc.impl.pub.ace;

import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceInsertBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUpdateBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceDeleteBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceSendApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUnSendApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_entryacceptancePubServiceImpl {
	// ����
	public AggEntryacceptanceVO[] pubinsertBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggEntryacceptanceVO> transferTool = new BillTransferTool<AggEntryacceptanceVO>(
					clientFullVOs);
			// ����BP
			AceHql_entryacceptanceInsertBP action = new AceHql_entryacceptanceInsertBP();
			AggEntryacceptanceVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHql_entryacceptanceDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggEntryacceptanceVO[] pubupdateBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggEntryacceptanceVO> transferTool = new BillTransferTool<AggEntryacceptanceVO>(
					clientFullVOs);
			AceHql_entryacceptanceUpdateBP bp = new AceHql_entryacceptanceUpdateBP();
			AggEntryacceptanceVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggEntryacceptanceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggEntryacceptanceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggEntryacceptanceVO> query = new BillLazyQuery<AggEntryacceptanceVO>(
					AggEntryacceptanceVO.class);
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
	public AggEntryacceptanceVO[] pubsendapprovebills(
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills)
			throws BusinessException {
		AceHql_entryacceptanceSendApproveBP bp = new AceHql_entryacceptanceSendApproveBP();
		AggEntryacceptanceVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggEntryacceptanceVO[] pubunsendapprovebills(
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills)
			throws BusinessException {
		AceHql_entryacceptanceUnSendApproveBP bp = new AceHql_entryacceptanceUnSendApproveBP();
		AggEntryacceptanceVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggEntryacceptanceVO[] pubapprovebills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_entryacceptanceApproveBP bp = new AceHql_entryacceptanceApproveBP();
		AggEntryacceptanceVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggEntryacceptanceVO[] pubunapprovebills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_entryacceptanceUnApproveBP bp = new AceHql_entryacceptanceUnApproveBP();
		AggEntryacceptanceVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}