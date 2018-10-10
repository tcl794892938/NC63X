package nc.impl.pub.ace;

import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractInsertBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractUpdateBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractDeleteBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractSendApproveBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractUnSendApproveBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractApproveBP;
import nc.bs.zl.ld_parkcontract.ace.bp.AceLd_parkcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_parkcontract.AggParkcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_parkcontractPubServiceImpl {
	// 新增
	public AggParkcontractVO[] pubinsertBills(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggParkcontractVO> transferTool = new BillTransferTool<AggParkcontractVO>(
					clientFullVOs);
			// 调用BP
			AceLd_parkcontractInsertBP action = new AceLd_parkcontractInsertBP();
			AggParkcontractVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLd_parkcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggParkcontractVO[] pubupdateBills(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggParkcontractVO> transferTool = new BillTransferTool<AggParkcontractVO>(
					clientFullVOs);
			AceLd_parkcontractUpdateBP bp = new AceLd_parkcontractUpdateBP();
			AggParkcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggParkcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggParkcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggParkcontractVO> query = new BillLazyQuery<AggParkcontractVO>(
					AggParkcontractVO.class);
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
	public AggParkcontractVO[] pubsendapprovebills(
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills)
			throws BusinessException {
		AceLd_parkcontractSendApproveBP bp = new AceLd_parkcontractSendApproveBP();
		AggParkcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggParkcontractVO[] pubunsendapprovebills(
			AggParkcontractVO[] clientFullVOs, AggParkcontractVO[] originBills)
			throws BusinessException {
		AceLd_parkcontractUnSendApproveBP bp = new AceLd_parkcontractUnSendApproveBP();
		AggParkcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggParkcontractVO[] pubapprovebills(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_parkcontractApproveBP bp = new AceLd_parkcontractApproveBP();
		AggParkcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggParkcontractVO[] pubunapprovebills(AggParkcontractVO[] clientFullVOs,
			AggParkcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_parkcontractUnApproveBP bp = new AceLd_parkcontractUnApproveBP();
		AggParkcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}