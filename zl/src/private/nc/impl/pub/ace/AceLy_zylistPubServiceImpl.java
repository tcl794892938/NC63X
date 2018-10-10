package nc.impl.pub.ace;

import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistInsertBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistUpdateBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistDeleteBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistSendApproveBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistUnSendApproveBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistApproveBP;
import nc.bs.zl.ly_zylist.ace.bp.AceLy_zylistUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_zylist.AggZylistVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_zylistPubServiceImpl {
	// ����
	public AggZylistVO[] pubinsertBills(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggZylistVO> transferTool = new BillTransferTool<AggZylistVO>(
					clientFullVOs);
			// ����BP
			AceLy_zylistInsertBP action = new AceLy_zylistInsertBP();
			AggZylistVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLy_zylistDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggZylistVO[] pubupdateBills(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggZylistVO> transferTool = new BillTransferTool<AggZylistVO>(
					clientFullVOs);
			AceLy_zylistUpdateBP bp = new AceLy_zylistUpdateBP();
			AggZylistVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggZylistVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggZylistVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggZylistVO> query = new BillLazyQuery<AggZylistVO>(
					AggZylistVO.class);
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
	public AggZylistVO[] pubsendapprovebills(
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills)
			throws BusinessException {
		AceLy_zylistSendApproveBP bp = new AceLy_zylistSendApproveBP();
		AggZylistVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggZylistVO[] pubunsendapprovebills(
			AggZylistVO[] clientFullVOs, AggZylistVO[] originBills)
			throws BusinessException {
		AceLy_zylistUnSendApproveBP bp = new AceLy_zylistUnSendApproveBP();
		AggZylistVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggZylistVO[] pubapprovebills(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_zylistApproveBP bp = new AceLy_zylistApproveBP();
		AggZylistVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggZylistVO[] pubunapprovebills(AggZylistVO[] clientFullVOs,
			AggZylistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_zylistUnApproveBP bp = new AceLy_zylistUnApproveBP();
		AggZylistVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}