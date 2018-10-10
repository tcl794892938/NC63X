package nc.impl.pub.ace;

import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistInsertBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUpdateBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistDeleteBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistSendApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUnSendApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_bslistPubServiceImpl {
	// ����
	public AggBslistVO[] pubinsertBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBslistVO> transferTool = new BillTransferTool<AggBslistVO>(
					clientFullVOs);
			// ����BP
			AceLy_bslistInsertBP action = new AceLy_bslistInsertBP();
			AggBslistVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLy_bslistDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBslistVO[] pubupdateBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBslistVO> transferTool = new BillTransferTool<AggBslistVO>(
					clientFullVOs);
			AceLy_bslistUpdateBP bp = new AceLy_bslistUpdateBP();
			AggBslistVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBslistVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBslistVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBslistVO> query = new BillLazyQuery<AggBslistVO>(
					AggBslistVO.class);
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
	public AggBslistVO[] pubsendapprovebills(
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills)
			throws BusinessException {
		AceLy_bslistSendApproveBP bp = new AceLy_bslistSendApproveBP();
		AggBslistVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggBslistVO[] pubunsendapprovebills(
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills)
			throws BusinessException {
		AceLy_bslistUnSendApproveBP bp = new AceLy_bslistUnSendApproveBP();
		AggBslistVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggBslistVO[] pubapprovebills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_bslistApproveBP bp = new AceLy_bslistApproveBP();
		AggBslistVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggBslistVO[] pubunapprovebills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_bslistUnApproveBP bp = new AceLy_bslistUnApproveBP();
		AggBslistVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}