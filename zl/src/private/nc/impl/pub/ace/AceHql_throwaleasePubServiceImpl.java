package nc.impl.pub.ace;

import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseInsertBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUpdateBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseDeleteBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseSendApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUnSendApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseApproveBP;
import nc.bs.zl.hql_throwalease.ace.bp.AceHql_throwaleaseUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.hql_throwalease.AggThrowaleaseVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHql_throwaleasePubServiceImpl {
	// 新增
	public AggThrowaleaseVO[] pubinsertBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggThrowaleaseVO> transferTool = new BillTransferTool<AggThrowaleaseVO>(
					clientFullVOs);
			// 调用BP
			AceHql_throwaleaseInsertBP action = new AceHql_throwaleaseInsertBP();
			AggThrowaleaseVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHql_throwaleaseDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggThrowaleaseVO[] pubupdateBills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggThrowaleaseVO> transferTool = new BillTransferTool<AggThrowaleaseVO>(
					clientFullVOs);
			AceHql_throwaleaseUpdateBP bp = new AceHql_throwaleaseUpdateBP();
			AggThrowaleaseVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggThrowaleaseVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggThrowaleaseVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggThrowaleaseVO> query = new BillLazyQuery<AggThrowaleaseVO>(
					AggThrowaleaseVO.class);
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
	public AggThrowaleaseVO[] pubsendapprovebills(
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills)
			throws BusinessException {
		AceHql_throwaleaseSendApproveBP bp = new AceHql_throwaleaseSendApproveBP();
		AggThrowaleaseVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggThrowaleaseVO[] pubunsendapprovebills(
			AggThrowaleaseVO[] clientFullVOs, AggThrowaleaseVO[] originBills)
			throws BusinessException {
		AceHql_throwaleaseUnSendApproveBP bp = new AceHql_throwaleaseUnSendApproveBP();
		AggThrowaleaseVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggThrowaleaseVO[] pubapprovebills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_throwaleaseApproveBP bp = new AceHql_throwaleaseApproveBP();
		AggThrowaleaseVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggThrowaleaseVO[] pubunapprovebills(AggThrowaleaseVO[] clientFullVOs,
			AggThrowaleaseVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHql_throwaleaseUnApproveBP bp = new AceHql_throwaleaseUnApproveBP();
		AggThrowaleaseVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}