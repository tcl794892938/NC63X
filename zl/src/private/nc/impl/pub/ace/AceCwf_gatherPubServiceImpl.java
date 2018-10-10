package nc.impl.pub.ace;

import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherInsertBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherUpdateBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherDeleteBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherSendApproveBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherUnSendApproveBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherApproveBP;
import nc.bs.zl.cwf_gather.ace.bp.AceCwf_gatherUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_gather.AggGatherVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCwf_gatherPubServiceImpl {
	// ����
	public AggGatherVO[] pubinsertBills(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggGatherVO> transferTool = new BillTransferTool<AggGatherVO>(
					clientFullVOs);
			// ����BP
			AceCwf_gatherInsertBP action = new AceCwf_gatherInsertBP();
			AggGatherVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceCwf_gatherDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggGatherVO[] pubupdateBills(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggGatherVO> transferTool = new BillTransferTool<AggGatherVO>(
					clientFullVOs);
			AceCwf_gatherUpdateBP bp = new AceCwf_gatherUpdateBP();
			AggGatherVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggGatherVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggGatherVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggGatherVO> query = new BillLazyQuery<AggGatherVO>(
					AggGatherVO.class);
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
	public AggGatherVO[] pubsendapprovebills(
			AggGatherVO[] clientFullVOs, AggGatherVO[] originBills)
			throws BusinessException {
		AceCwf_gatherSendApproveBP bp = new AceCwf_gatherSendApproveBP();
		AggGatherVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggGatherVO[] pubunsendapprovebills(
			AggGatherVO[] clientFullVOs, AggGatherVO[] originBills)
			throws BusinessException {
		AceCwf_gatherUnSendApproveBP bp = new AceCwf_gatherUnSendApproveBP();
		AggGatherVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggGatherVO[] pubapprovebills(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_gatherApproveBP bp = new AceCwf_gatherApproveBP();
		AggGatherVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggGatherVO[] pubunapprovebills(AggGatherVO[] clientFullVOs,
			AggGatherVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_gatherUnApproveBP bp = new AceCwf_gatherUnApproveBP();
		AggGatherVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}