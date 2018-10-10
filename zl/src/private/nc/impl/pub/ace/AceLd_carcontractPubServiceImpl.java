package nc.impl.pub.ace;

import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractInsertBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUpdateBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractDeleteBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractSendApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUnSendApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractApproveBP;
import nc.bs.zl.ld_carcontract.ace.bp.AceLd_carcontractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_carcontract.AggCarcontractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_carcontractPubServiceImpl {
	// 新增
	public AggCarcontractVO[] pubinsertBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggCarcontractVO> transferTool = new BillTransferTool<AggCarcontractVO>(
					clientFullVOs);
			// 调用BP
			AceLd_carcontractInsertBP action = new AceLd_carcontractInsertBP();
			AggCarcontractVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLd_carcontractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCarcontractVO[] pubupdateBills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCarcontractVO> transferTool = new BillTransferTool<AggCarcontractVO>(
					clientFullVOs);
			AceLd_carcontractUpdateBP bp = new AceLd_carcontractUpdateBP();
			AggCarcontractVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarcontractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarcontractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarcontractVO> query = new BillLazyQuery<AggCarcontractVO>(
					AggCarcontractVO.class);
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
	public AggCarcontractVO[] pubsendapprovebills(
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills)
			throws BusinessException {
		AceLd_carcontractSendApproveBP bp = new AceLd_carcontractSendApproveBP();
		AggCarcontractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCarcontractVO[] pubunsendapprovebills(
			AggCarcontractVO[] clientFullVOs, AggCarcontractVO[] originBills)
			throws BusinessException {
		AceLd_carcontractUnSendApproveBP bp = new AceLd_carcontractUnSendApproveBP();
		AggCarcontractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCarcontractVO[] pubapprovebills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_carcontractApproveBP bp = new AceLd_carcontractApproveBP();
		AggCarcontractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCarcontractVO[] pubunapprovebills(AggCarcontractVO[] clientFullVOs,
			AggCarcontractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_carcontractUnApproveBP bp = new AceLd_carcontractUnApproveBP();
		AggCarcontractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}