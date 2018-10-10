package nc.impl.pub.ace;

import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditInsertBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUpdateBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditDeleteBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditSendApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUnSendApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCwf_carconeditPubServiceImpl {
	// ����
	public AggCarconeditVO[] pubinsertBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggCarconeditVO> transferTool = new BillTransferTool<AggCarconeditVO>(
					clientFullVOs);
			// ����BP
			AceCwf_carconeditInsertBP action = new AceCwf_carconeditInsertBP();
			AggCarconeditVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceCwf_carconeditDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggCarconeditVO[] pubupdateBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggCarconeditVO> transferTool = new BillTransferTool<AggCarconeditVO>(
					clientFullVOs);
			AceCwf_carconeditUpdateBP bp = new AceCwf_carconeditUpdateBP();
			AggCarconeditVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarconeditVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarconeditVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarconeditVO> query = new BillLazyQuery<AggCarconeditVO>(
					AggCarconeditVO.class);
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
	public AggCarconeditVO[] pubsendapprovebills(
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills)
			throws BusinessException {
		AceCwf_carconeditSendApproveBP bp = new AceCwf_carconeditSendApproveBP();
		AggCarconeditVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggCarconeditVO[] pubunsendapprovebills(
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills)
			throws BusinessException {
		AceCwf_carconeditUnSendApproveBP bp = new AceCwf_carconeditUnSendApproveBP();
		AggCarconeditVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggCarconeditVO[] pubapprovebills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_carconeditApproveBP bp = new AceCwf_carconeditApproveBP();
		AggCarconeditVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggCarconeditVO[] pubunapprovebills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_carconeditUnApproveBP bp = new AceCwf_carconeditUnApproveBP();
		AggCarconeditVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}