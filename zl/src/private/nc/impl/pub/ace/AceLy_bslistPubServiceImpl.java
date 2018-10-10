package nc.impl.pub.ace;

import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistInsertBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUpdateBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistDeleteBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistSendApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUnSendApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistApproveBP;
import nc.bs.zl.ly_bslist.ace.bp.AceLy_bslistUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_bslist.AggBslistVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_bslistPubServiceImpl {
	// 新增
	public AggBslistVO[] pubinsertBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBslistVO> transferTool = new BillTransferTool<AggBslistVO>(
					clientFullVOs);
			// 调用BP
			AceLy_bslistInsertBP action = new AceLy_bslistInsertBP();
			AggBslistVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLy_bslistDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBslistVO[] pubupdateBills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBslistVO> transferTool = new BillTransferTool<AggBslistVO>(
					clientFullVOs);
			AceLy_bslistUpdateBP bp = new AceLy_bslistUpdateBP();
			AggBslistVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBslistVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBslistVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBslistVO> query = new BillLazyQuery<AggBslistVO>(
					AggBslistVO.class);
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
	public AggBslistVO[] pubsendapprovebills(
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills)
			throws BusinessException {
		AceLy_bslistSendApproveBP bp = new AceLy_bslistSendApproveBP();
		AggBslistVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggBslistVO[] pubunsendapprovebills(
			AggBslistVO[] clientFullVOs, AggBslistVO[] originBills)
			throws BusinessException {
		AceLy_bslistUnSendApproveBP bp = new AceLy_bslistUnSendApproveBP();
		AggBslistVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggBslistVO[] pubapprovebills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_bslistApproveBP bp = new AceLy_bslistApproveBP();
		AggBslistVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggBslistVO[] pubunapprovebills(AggBslistVO[] clientFullVOs,
			AggBslistVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_bslistUnApproveBP bp = new AceLy_bslistUnApproveBP();
		AggBslistVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}