package nc.impl.pub.ace;

import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceInsertBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUpdateBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceDeleteBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceSendApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUnSendApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceApproveBP;
import nc.bs.zl.hql_entryacceptance.ace.bp.AceHql_entryacceptanceUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_entryacceptance.AggEntryacceptanceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_entryacceptancePubServiceImpl {
	// 新增
	public AggEntryacceptanceVO[] pubinsertBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggEntryacceptanceVO> transferTool = new BillTransferTool<AggEntryacceptanceVO>(
					clientFullVOs);
			// 调用BP
			AceHql_entryacceptanceInsertBP action = new AceHql_entryacceptanceInsertBP();
			AggEntryacceptanceVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHql_entryacceptanceDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggEntryacceptanceVO[] pubupdateBills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggEntryacceptanceVO> transferTool = new BillTransferTool<AggEntryacceptanceVO>(
					clientFullVOs);
			AceHql_entryacceptanceUpdateBP bp = new AceHql_entryacceptanceUpdateBP();
			AggEntryacceptanceVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggEntryacceptanceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggEntryacceptanceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggEntryacceptanceVO> query = new BillLazyQuery<AggEntryacceptanceVO>(
					AggEntryacceptanceVO.class);
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
	public AggEntryacceptanceVO[] pubsendapprovebills(
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills)
			throws BusinessException {
		AceHql_entryacceptanceSendApproveBP bp = new AceHql_entryacceptanceSendApproveBP();
		AggEntryacceptanceVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggEntryacceptanceVO[] pubunsendapprovebills(
			AggEntryacceptanceVO[] clientFullVOs, AggEntryacceptanceVO[] originBills)
			throws BusinessException {
		AceHql_entryacceptanceUnSendApproveBP bp = new AceHql_entryacceptanceUnSendApproveBP();
		AggEntryacceptanceVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggEntryacceptanceVO[] pubapprovebills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_entryacceptanceApproveBP bp = new AceHql_entryacceptanceApproveBP();
		AggEntryacceptanceVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggEntryacceptanceVO[] pubunapprovebills(AggEntryacceptanceVO[] clientFullVOs,
			AggEntryacceptanceVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_entryacceptanceUnApproveBP bp = new AceHql_entryacceptanceUnApproveBP();
		AggEntryacceptanceVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}