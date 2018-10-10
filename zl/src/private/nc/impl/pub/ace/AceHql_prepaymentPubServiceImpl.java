package nc.impl.pub.ace;

import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentInsertBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUpdateBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentDeleteBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentSendApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUnSendApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentApproveBP;
import nc.bs.zl.hql_prepayment.ace.bp.AceHql_prepaymentUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_prepayment.AggPrepaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_prepaymentPubServiceImpl {
	// 新增
	public AggPrepaymentVO[] pubinsertBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggPrepaymentVO> transferTool = new BillTransferTool<AggPrepaymentVO>(
					clientFullVOs);
			// 调用BP
			AceHql_prepaymentInsertBP action = new AceHql_prepaymentInsertBP();
			AggPrepaymentVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHql_prepaymentDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggPrepaymentVO[] pubupdateBills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggPrepaymentVO> transferTool = new BillTransferTool<AggPrepaymentVO>(
					clientFullVOs);
			AceHql_prepaymentUpdateBP bp = new AceHql_prepaymentUpdateBP();
			AggPrepaymentVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPrepaymentVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPrepaymentVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPrepaymentVO> query = new BillLazyQuery<AggPrepaymentVO>(
					AggPrepaymentVO.class);
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
	public AggPrepaymentVO[] pubsendapprovebills(
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills)
			throws BusinessException {
		AceHql_prepaymentSendApproveBP bp = new AceHql_prepaymentSendApproveBP();
		AggPrepaymentVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggPrepaymentVO[] pubunsendapprovebills(
			AggPrepaymentVO[] clientFullVOs, AggPrepaymentVO[] originBills)
			throws BusinessException {
		AceHql_prepaymentUnSendApproveBP bp = new AceHql_prepaymentUnSendApproveBP();
		AggPrepaymentVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggPrepaymentVO[] pubapprovebills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_prepaymentApproveBP bp = new AceHql_prepaymentApproveBP();
		AggPrepaymentVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggPrepaymentVO[] pubunapprovebills(AggPrepaymentVO[] clientFullVOs,
			AggPrepaymentVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_prepaymentUnApproveBP bp = new AceHql_prepaymentUnApproveBP();
		AggPrepaymentVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}