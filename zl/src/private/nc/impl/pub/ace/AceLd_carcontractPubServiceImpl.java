package nc.impl.pub.ace;

import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractInsertBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUpdateBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractDeleteBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractSendApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUnSendApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_carcontractPubServiceImpl {
	// ����
	public AggCarcontractVO[] pubinsertBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggCarcontractVO> transferTool = new BillTransferTool<AggCarcontractVO>(
					clientFullVOs);
			// ����BP
			AceLd_carcontractInsertBP action = new AceLd_carcontractInsertBP();
			AggCarcontractVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLd_carcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggCarcontractVO[] pubupdateBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggCarcontractVO> transferTool = new BillTransferTool<AggCarcontractVO>(
					clientFullVOs);
			AceLd_carcontractUpdateBP bp = new AceLd_carcontractUpdateBP();
			AggCarcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarcontractVO> query = new BillLazyQuery<AggCarcontractVO>(
					AggCarcontractVO.class);
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
	public AggCarcontractVO[] pubsendapprovebills(
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills)
			throws BusinessException {
		AceLd_carcontractSendApproveBP bp = new AceLd_carcontractSendApproveBP();
		AggCarcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggCarcontractVO[] pubunsendapprovebills(
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills)
			throws BusinessException {
		AceLd_carcontractUnSendApproveBP bp = new AceLd_carcontractUnSendApproveBP();
		AggCarcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggCarcontractVO[] pubapprovebills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_carcontractApproveBP bp = new AceLd_carcontractApproveBP();
		AggCarcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggCarcontractVO[] pubunapprovebills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_carcontractUnApproveBP bp = new AceLd_carcontractUnApproveBP();
		AggCarcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}