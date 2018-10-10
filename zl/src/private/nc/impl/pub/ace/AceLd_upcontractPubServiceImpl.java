package nc.impl.pub.ace;

import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractInsertBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUpdateBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractDeleteBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractSendApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUnSendApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractApproveBP;
import nc.bs.zl.ld_upcontract.ace.bp.AceLd_upcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_upcontract.AggUpcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_upcontractPubServiceImpl {
	// 新增
	public AggUpcontractVO[] pubinsertBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggUpcontractVO> transferTool = new BillTransferTool<AggUpcontractVO>(
					clientFullVOs);
			// 调用BP
			AceLd_upcontractInsertBP action = new AceLd_upcontractInsertBP();
			AggUpcontractVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLd_upcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggUpcontractVO[] pubupdateBills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggUpcontractVO> transferTool = new BillTransferTool<AggUpcontractVO>(
					clientFullVOs);
			AceLd_upcontractUpdateBP bp = new AceLd_upcontractUpdateBP();
			AggUpcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggUpcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggUpcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggUpcontractVO> query = new BillLazyQuery<AggUpcontractVO>(
					AggUpcontractVO.class);
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
	public AggUpcontractVO[] pubsendapprovebills(
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills)
			throws BusinessException {
		AceLd_upcontractSendApproveBP bp = new AceLd_upcontractSendApproveBP();
		AggUpcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggUpcontractVO[] pubunsendapprovebills(
			AggUpcontractVO[] clientFullVOs, AggUpcontractVO[] originBills)
			throws BusinessException {
		AceLd_upcontractUnSendApproveBP bp = new AceLd_upcontractUnSendApproveBP();
		AggUpcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggUpcontractVO[] pubapprovebills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_upcontractApproveBP bp = new AceLd_upcontractApproveBP();
		AggUpcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggUpcontractVO[] pubunapprovebills(AggUpcontractVO[] clientFullVOs,
			AggUpcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_upcontractUnApproveBP bp = new AceLd_upcontractUnApproveBP();
		AggUpcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}