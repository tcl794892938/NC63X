package nc.impl.pub.ace;

import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterInsertBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUpdateBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterDeleteBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterSendApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUnSendApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterApproveBP;
import nc.bs.zl.ld_kpregister.ace.bp.AceLd_kpregisterUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ld_kpregister.AggKpregisterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLd_kpregisterPubServiceImpl {
	// 新增
	public AggKpregisterVO[] pubinsertBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggKpregisterVO> transferTool = new BillTransferTool<AggKpregisterVO>(
					clientFullVOs);
			// 调用BP
			AceLd_kpregisterInsertBP action = new AceLd_kpregisterInsertBP();
			AggKpregisterVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLd_kpregisterDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggKpregisterVO[] pubupdateBills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggKpregisterVO> transferTool = new BillTransferTool<AggKpregisterVO>(
					clientFullVOs);
			AceLd_kpregisterUpdateBP bp = new AceLd_kpregisterUpdateBP();
			AggKpregisterVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggKpregisterVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggKpregisterVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggKpregisterVO> query = new BillLazyQuery<AggKpregisterVO>(
					AggKpregisterVO.class);
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
	public AggKpregisterVO[] pubsendapprovebills(
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills)
			throws BusinessException {
		AceLd_kpregisterSendApproveBP bp = new AceLd_kpregisterSendApproveBP();
		AggKpregisterVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggKpregisterVO[] pubunsendapprovebills(
			AggKpregisterVO[] clientFullVOs, AggKpregisterVO[] originBills)
			throws BusinessException {
		AceLd_kpregisterUnSendApproveBP bp = new AceLd_kpregisterUnSendApproveBP();
		AggKpregisterVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggKpregisterVO[] pubapprovebills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_kpregisterApproveBP bp = new AceLd_kpregisterApproveBP();
		AggKpregisterVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggKpregisterVO[] pubunapprovebills(AggKpregisterVO[] clientFullVOs,
			AggKpregisterVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLd_kpregisterUnApproveBP bp = new AceLd_kpregisterUnApproveBP();
		AggKpregisterVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}