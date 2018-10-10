package nc.impl.pub.ace;

import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentInsertBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUpdateBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentDeleteBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentSendApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUnSendApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentApproveBP;
import nc.bs.zl.hql_payment.ace.bp.AceHql_paymentUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_payment.AggPaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_paymentPubServiceImpl {
	// 新增
	public AggPaymentVO[] pubinsertBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggPaymentVO> transferTool = new BillTransferTool<AggPaymentVO>(
					clientFullVOs);
			// 调用BP
			AceHql_paymentInsertBP action = new AceHql_paymentInsertBP();
			AggPaymentVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHql_paymentDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggPaymentVO[] pubupdateBills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggPaymentVO> transferTool = new BillTransferTool<AggPaymentVO>(
					clientFullVOs);
			AceHql_paymentUpdateBP bp = new AceHql_paymentUpdateBP();
			AggPaymentVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPaymentVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPaymentVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPaymentVO> query = new BillLazyQuery<AggPaymentVO>(
					AggPaymentVO.class);
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
	public AggPaymentVO[] pubsendapprovebills(
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills)
			throws BusinessException {
		AceHql_paymentSendApproveBP bp = new AceHql_paymentSendApproveBP();
		AggPaymentVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggPaymentVO[] pubunsendapprovebills(
			AggPaymentVO[] clientFullVOs, AggPaymentVO[] originBills)
			throws BusinessException {
		AceHql_paymentUnSendApproveBP bp = new AceHql_paymentUnSendApproveBP();
		AggPaymentVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggPaymentVO[] pubapprovebills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_paymentApproveBP bp = new AceHql_paymentApproveBP();
		AggPaymentVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggPaymentVO[] pubunapprovebills(AggPaymentVO[] clientFullVOs,
			AggPaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_paymentUnApproveBP bp = new AceHql_paymentUnApproveBP();
		AggPaymentVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}