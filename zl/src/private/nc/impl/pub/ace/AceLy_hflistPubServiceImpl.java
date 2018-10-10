package nc.impl.pub.ace;

import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistInsertBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistUpdateBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistDeleteBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistSendApproveBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistUnSendApproveBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistApproveBP;
import nc.bs.zl.ly_hflist.ace.bp.AceLy_hflistUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_hflist.AggHflistVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_hflistPubServiceImpl {
	// ����
	public AggHflistVO[] pubinsertBills(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggHflistVO> transferTool = new BillTransferTool<AggHflistVO>(
					clientFullVOs);
			// ����BP
			AceLy_hflistInsertBP action = new AceLy_hflistInsertBP();
			AggHflistVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLy_hflistDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggHflistVO[] pubupdateBills(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggHflistVO> transferTool = new BillTransferTool<AggHflistVO>(
					clientFullVOs);
			AceLy_hflistUpdateBP bp = new AceLy_hflistUpdateBP();
			AggHflistVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggHflistVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggHflistVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggHflistVO> query = new BillLazyQuery<AggHflistVO>(
					AggHflistVO.class);
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
	public AggHflistVO[] pubsendapprovebills(
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills)
			throws BusinessException {
		AceLy_hflistSendApproveBP bp = new AceLy_hflistSendApproveBP();
		AggHflistVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggHflistVO[] pubunsendapprovebills(
			AggHflistVO[] clientFullVOs, AggHflistVO[] originBills)
			throws BusinessException {
		AceLy_hflistUnSendApproveBP bp = new AceLy_hflistUnSendApproveBP();
		AggHflistVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggHflistVO[] pubapprovebills(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_hflistApproveBP bp = new AceLy_hflistApproveBP();
		AggHflistVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggHflistVO[] pubunapprovebills(AggHflistVO[] clientFullVOs,
			AggHflistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_hflistUnApproveBP bp = new AceLy_hflistUnApproveBP();
		AggHflistVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}