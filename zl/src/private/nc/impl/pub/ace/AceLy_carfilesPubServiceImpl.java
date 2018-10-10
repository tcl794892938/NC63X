package nc.impl.pub.ace;

import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesInsertBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUpdateBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesDeleteBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesSendApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUnSendApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesApproveBP;
import nc.bs.zl.ly_carfiles.ace.bp.AceLy_carfilesUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_carfiles.AggCarFilesVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_carfilesPubServiceImpl {
	// 新增
	public AggCarFilesVO[] pubinsertBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggCarFilesVO> transferTool = new BillTransferTool<AggCarFilesVO>(
					clientFullVOs);
			// 调用BP
			AceLy_carfilesInsertBP action = new AceLy_carfilesInsertBP();
			AggCarFilesVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLy_carfilesDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCarFilesVO[] pubupdateBills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCarFilesVO> transferTool = new BillTransferTool<AggCarFilesVO>(
					clientFullVOs);
			AceLy_carfilesUpdateBP bp = new AceLy_carfilesUpdateBP();
			AggCarFilesVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarFilesVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarFilesVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarFilesVO> query = new BillLazyQuery<AggCarFilesVO>(
					AggCarFilesVO.class);
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
	public AggCarFilesVO[] pubsendapprovebills(
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills)
			throws BusinessException {
		AceLy_carfilesSendApproveBP bp = new AceLy_carfilesSendApproveBP();
		AggCarFilesVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCarFilesVO[] pubunsendapprovebills(
			AggCarFilesVO[] clientFullVOs, AggCarFilesVO[] originBills)
			throws BusinessException {
		AceLy_carfilesUnSendApproveBP bp = new AceLy_carfilesUnSendApproveBP();
		AggCarFilesVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCarFilesVO[] pubapprovebills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_carfilesApproveBP bp = new AceLy_carfilesApproveBP();
		AggCarFilesVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCarFilesVO[] pubunapprovebills(AggCarFilesVO[] clientFullVOs,
			AggCarFilesVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_carfilesUnApproveBP bp = new AceLy_carfilesUnApproveBP();
		AggCarFilesVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}