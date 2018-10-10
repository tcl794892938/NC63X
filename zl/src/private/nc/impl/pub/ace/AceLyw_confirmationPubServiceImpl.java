package nc.impl.pub.ace;

import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationInsertBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUpdateBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationDeleteBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationSendApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUnSendApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationApproveBP;
import nc.bs.zl.lyw_confirmation.ace.bp.AceLyw_confirmationUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.lyw_confirmation.AggConfirmationVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLyw_confirmationPubServiceImpl {
	// 新增
	public AggConfirmationVO[] pubinsertBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggConfirmationVO> transferTool = new BillTransferTool<AggConfirmationVO>(
					clientFullVOs);
			// 调用BP
			AceLyw_confirmationInsertBP action = new AceLyw_confirmationInsertBP();
			AggConfirmationVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLyw_confirmationDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggConfirmationVO[] pubupdateBills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggConfirmationVO> transferTool = new BillTransferTool<AggConfirmationVO>(
					clientFullVOs);
			AceLyw_confirmationUpdateBP bp = new AceLyw_confirmationUpdateBP();
			AggConfirmationVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggConfirmationVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggConfirmationVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggConfirmationVO> query = new BillLazyQuery<AggConfirmationVO>(
					AggConfirmationVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggConfirmationVO[] pubsendapprovebills(
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills)
			throws BusinessException {
		AceLyw_confirmationSendApproveBP bp = new AceLyw_confirmationSendApproveBP();
		AggConfirmationVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggConfirmationVO[] pubunsendapprovebills(
			AggConfirmationVO[] clientFullVOs, AggConfirmationVO[] originBills)
			throws BusinessException {
		AceLyw_confirmationUnSendApproveBP bp = new AceLyw_confirmationUnSendApproveBP();
		AggConfirmationVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggConfirmationVO[] pubapprovebills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_confirmationApproveBP bp = new AceLyw_confirmationApproveBP();
		AggConfirmationVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggConfirmationVO[] pubunapprovebills(AggConfirmationVO[] clientFullVOs,
			AggConfirmationVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLyw_confirmationUnApproveBP bp = new AceLyw_confirmationUnApproveBP();
		AggConfirmationVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}