package nc.impl.pub.ace;

import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyInsertBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUpdateBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyDeleteBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneySendApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUnSendApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_sgmoneyPubServiceImpl {
	// ����
	public AggSgMoneyVO[] pubinsertBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggSgMoneyVO> transferTool = new BillTransferTool<AggSgMoneyVO>(
					clientFullVOs);
			// ����BP
			AceLy_sgmoneyInsertBP action = new AceLy_sgmoneyInsertBP();
			AggSgMoneyVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceLy_sgmoneyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggSgMoneyVO[] pubupdateBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggSgMoneyVO> transferTool = new BillTransferTool<AggSgMoneyVO>(
					clientFullVOs);
			AceLy_sgmoneyUpdateBP bp = new AceLy_sgmoneyUpdateBP();
			AggSgMoneyVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSgMoneyVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSgMoneyVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSgMoneyVO> query = new BillLazyQuery<AggSgMoneyVO>(
					AggSgMoneyVO.class);
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
	public AggSgMoneyVO[] pubsendapprovebills(
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills)
			throws BusinessException {
		AceLy_sgmoneySendApproveBP bp = new AceLy_sgmoneySendApproveBP();
		AggSgMoneyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggSgMoneyVO[] pubunsendapprovebills(
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills)
			throws BusinessException {
		AceLy_sgmoneyUnSendApproveBP bp = new AceLy_sgmoneyUnSendApproveBP();
		AggSgMoneyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggSgMoneyVO[] pubapprovebills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_sgmoneyApproveBP bp = new AceLy_sgmoneyApproveBP();
		AggSgMoneyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggSgMoneyVO[] pubunapprovebills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_sgmoneyUnApproveBP bp = new AceLy_sgmoneyUnApproveBP();
		AggSgMoneyVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}