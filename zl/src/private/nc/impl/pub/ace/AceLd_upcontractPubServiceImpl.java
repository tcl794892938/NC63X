package nc.impl.pub.ace;

import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractInsertBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUpdateBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractDeleteBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractSendApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUnSendApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_upcontractPubServiceImpl {
	// ����
	public AggUpcontractVO[] pubinsertBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggUpcontractVO> transferTool = new BillTransferTool<AggUpcontractVO>(
					clientFullVOs);
			// ����BP
			AceLd_upcontractInsertBP action = new AceLd_upcontractInsertBP();
			AggUpcontractVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLd_upcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggUpcontractVO[] pubupdateBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggUpcontractVO> transferTool = new BillTransferTool<AggUpcontractVO>(
					clientFullVOs);
			AceLd_upcontractUpdateBP bp = new AceLd_upcontractUpdateBP();
			AggUpcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggUpcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggUpcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggUpcontractVO> query = new BillLazyQuery<AggUpcontractVO>(
					AggUpcontractVO.class);
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
	public AggUpcontractVO[] pubsendapprovebills(
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills)
			throws BusinessException {
		AceLd_upcontractSendApproveBP bp = new AceLd_upcontractSendApproveBP();
		AggUpcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggUpcontractVO[] pubunsendapprovebills(
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills)
			throws BusinessException {
		AceLd_upcontractUnSendApproveBP bp = new AceLd_upcontractUnSendApproveBP();
		AggUpcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggUpcontractVO[] pubapprovebills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_upcontractApproveBP bp = new AceLd_upcontractApproveBP();
		AggUpcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggUpcontractVO[] pubunapprovebills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_upcontractUnApproveBP bp = new AceLd_upcontractUnApproveBP();
		AggUpcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}