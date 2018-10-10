package nc.impl.pub.ace;

import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseInsertBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUpdateBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseDeleteBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseSendApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUnSendApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_throwaleasePubServiceImpl {
	// ����
	public AggThrowaleaseVO[] pubinsertBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggThrowaleaseVO> transferTool = new BillTransferTool<AggThrowaleaseVO>(
					clientFullVOs);
			// ����BP
			AceHql_throwaleaseInsertBP action = new AceHql_throwaleaseInsertBP();
			AggThrowaleaseVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHql_throwaleaseDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggThrowaleaseVO[] pubupdateBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggThrowaleaseVO> transferTool = new BillTransferTool<AggThrowaleaseVO>(
					clientFullVOs);
			AceHql_throwaleaseUpdateBP bp = new AceHql_throwaleaseUpdateBP();
			AggThrowaleaseVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggThrowaleaseVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggThrowaleaseVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggThrowaleaseVO> query = new BillLazyQuery<AggThrowaleaseVO>(
					AggThrowaleaseVO.class);
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
	public AggThrowaleaseVO[] pubsendapprovebills(
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills)
			throws BusinessException {
		AceHql_throwaleaseSendApproveBP bp = new AceHql_throwaleaseSendApproveBP();
		AggThrowaleaseVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggThrowaleaseVO[] pubunsendapprovebills(
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills)
			throws BusinessException {
		AceHql_throwaleaseUnSendApproveBP bp = new AceHql_throwaleaseUnSendApproveBP();
		AggThrowaleaseVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggThrowaleaseVO[] pubapprovebills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_throwaleaseApproveBP bp = new AceHql_throwaleaseApproveBP();
		AggThrowaleaseVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggThrowaleaseVO[] pubunapprovebills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_throwaleaseUnApproveBP bp = new AceHql_throwaleaseUnApproveBP();
		AggThrowaleaseVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}