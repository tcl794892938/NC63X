package nc.impl.pub.ace;

import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractInsertBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractUpdateBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractDeleteBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractSendApproveBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractUnSendApproveBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractApproveBP;
import nc.bs.zl.ld_mdcontract.ace.bp.AceLd_mdcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_mdcontract.AggMdcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_mdcontractPubServiceImpl {
	// ����
	public AggMdcontractVO[] pubinsertBills(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggMdcontractVO> transferTool = new BillTransferTool<AggMdcontractVO>(
					clientFullVOs);
			// ����BP
			AceLd_mdcontractInsertBP action = new AceLd_mdcontractInsertBP();
			AggMdcontractVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLd_mdcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggMdcontractVO[] pubupdateBills(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggMdcontractVO> transferTool = new BillTransferTool<AggMdcontractVO>(
					clientFullVOs);
			AceLd_mdcontractUpdateBP bp = new AceLd_mdcontractUpdateBP();
			AggMdcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggMdcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggMdcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggMdcontractVO> query = new BillLazyQuery<AggMdcontractVO>(
					AggMdcontractVO.class);
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
	public AggMdcontractVO[] pubsendapprovebills(
			AggMdcontractVO[] clientFullVOs, AggMdcontractVO[] originBills)
			throws BusinessException {
		AceLd_mdcontractSendApproveBP bp = new AceLd_mdcontractSendApproveBP();
		AggMdcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggMdcontractVO[] pubunsendapprovebills(
			AggMdcontractVO[] clientFullVOs, AggMdcontractVO[] originBills)
			throws BusinessException {
		AceLd_mdcontractUnSendApproveBP bp = new AceLd_mdcontractUnSendApproveBP();
		AggMdcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggMdcontractVO[] pubapprovebills(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_mdcontractApproveBP bp = new AceLd_mdcontractApproveBP();
		AggMdcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggMdcontractVO[] pubunapprovebills(AggMdcontractVO[] clientFullVOs,
			AggMdcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_mdcontractUnApproveBP bp = new AceLd_mdcontractUnApproveBP();
		AggMdcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}