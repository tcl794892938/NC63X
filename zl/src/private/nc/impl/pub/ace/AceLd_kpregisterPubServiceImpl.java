package nc.impl.pub.ace;

import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterInsertBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUpdateBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterDeleteBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterSendApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUnSendApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_kpregisterPubServiceImpl {
	// ����
	public AggKpregisterVO[] pubinsertBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggKpregisterVO> transferTool = new BillTransferTool<AggKpregisterVO>(
					clientFullVOs);
			// ����BP
			AceLd_kpregisterInsertBP action = new AceLd_kpregisterInsertBP();
			AggKpregisterVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLd_kpregisterDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggKpregisterVO[] pubupdateBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggKpregisterVO> transferTool = new BillTransferTool<AggKpregisterVO>(
					clientFullVOs);
			AceLd_kpregisterUpdateBP bp = new AceLd_kpregisterUpdateBP();
			AggKpregisterVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggKpregisterVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggKpregisterVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggKpregisterVO> query = new BillLazyQuery<AggKpregisterVO>(
					AggKpregisterVO.class);
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
	public AggKpregisterVO[] pubsendapprovebills(
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills)
			throws BusinessException {
		AceLd_kpregisterSendApproveBP bp = new AceLd_kpregisterSendApproveBP();
		AggKpregisterVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggKpregisterVO[] pubunsendapprovebills(
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills)
			throws BusinessException {
		AceLd_kpregisterUnSendApproveBP bp = new AceLd_kpregisterUnSendApproveBP();
		AggKpregisterVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggKpregisterVO[] pubapprovebills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_kpregisterApproveBP bp = new AceLd_kpregisterApproveBP();
		AggKpregisterVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggKpregisterVO[] pubunapprovebills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_kpregisterUnApproveBP bp = new AceLd_kpregisterUnApproveBP();
		AggKpregisterVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}