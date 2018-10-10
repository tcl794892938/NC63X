package nc.impl.pub.ace;

import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedInsertBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedUpdateBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedDeleteBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedSendApproveBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedUnSendApproveBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedApproveBP;
import nc.bs.zl.lyw_billconfirmed.ace.bp.AceLyw_billconfirmedUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.lyw_billconfirmed.AggBillconfirmedVO;

public abstract class AceLyw_billconfirmedPubServiceImpl {
	// ����
	public AggBillconfirmedVO[] pubinsertBills(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggBillconfirmedVO> transferTool = new BillTransferTool<AggBillconfirmedVO>(
					clientFullVOs);
			// ����BP
			AceLyw_billconfirmedInsertBP action = new AceLyw_billconfirmedInsertBP();
			AggBillconfirmedVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLyw_billconfirmedDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggBillconfirmedVO[] pubupdateBills(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggBillconfirmedVO> transferTool = new BillTransferTool<AggBillconfirmedVO>(
					clientFullVOs);
			AceLyw_billconfirmedUpdateBP bp = new AceLyw_billconfirmedUpdateBP();
			AggBillconfirmedVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBillconfirmedVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBillconfirmedVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBillconfirmedVO> query = new BillLazyQuery<AggBillconfirmedVO>(
					AggBillconfirmedVO.class);
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
	public AggBillconfirmedVO[] pubsendapprovebills(
			AggBillconfirmedVO[] clientFullVOs, AggBillconfirmedVO[] originBills)
			throws BusinessException {
		AceLyw_billconfirmedSendApproveBP bp = new AceLyw_billconfirmedSendApproveBP();
		AggBillconfirmedVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggBillconfirmedVO[] pubunsendapprovebills(
			AggBillconfirmedVO[] clientFullVOs, AggBillconfirmedVO[] originBills)
			throws BusinessException {
		AceLyw_billconfirmedUnSendApproveBP bp = new AceLyw_billconfirmedUnSendApproveBP();
		AggBillconfirmedVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggBillconfirmedVO[] pubapprovebills(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_billconfirmedApproveBP bp = new AceLyw_billconfirmedApproveBP();
		AggBillconfirmedVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggBillconfirmedVO[] pubunapprovebills(AggBillconfirmedVO[] clientFullVOs,
			AggBillconfirmedVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_billconfirmedUnApproveBP bp = new AceLyw_billconfirmedUnApproveBP();
		AggBillconfirmedVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}