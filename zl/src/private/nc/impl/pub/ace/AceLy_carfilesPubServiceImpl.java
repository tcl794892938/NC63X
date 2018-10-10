package nc.impl.pub.ace;

import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesInsertBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUpdateBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesDeleteBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesSendApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUnSendApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_carfilesPubServiceImpl {
	// ����
	public AggCarFilesVO[] pubinsertBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggCarFilesVO> transferTool = new BillTransferTool<AggCarFilesVO>(
					clientFullVOs);
			// ����BP
			AceLy_carfilesInsertBP action = new AceLy_carfilesInsertBP();
			AggCarFilesVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLy_carfilesDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggCarFilesVO[] pubupdateBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggCarFilesVO> transferTool = new BillTransferTool<AggCarFilesVO>(
					clientFullVOs);
			AceLy_carfilesUpdateBP bp = new AceLy_carfilesUpdateBP();
			AggCarFilesVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarFilesVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarFilesVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarFilesVO> query = new BillLazyQuery<AggCarFilesVO>(
					AggCarFilesVO.class);
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
	public AggCarFilesVO[] pubsendapprovebills(
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills)
			throws BusinessException {
		AceLy_carfilesSendApproveBP bp = new AceLy_carfilesSendApproveBP();
		AggCarFilesVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggCarFilesVO[] pubunsendapprovebills(
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills)
			throws BusinessException {
		AceLy_carfilesUnSendApproveBP bp = new AceLy_carfilesUnSendApproveBP();
		AggCarFilesVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggCarFilesVO[] pubapprovebills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_carfilesApproveBP bp = new AceLy_carfilesApproveBP();
		AggCarFilesVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggCarFilesVO[] pubunapprovebills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_carfilesUnApproveBP bp = new AceLy_carfilesUnApproveBP();
		AggCarFilesVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}