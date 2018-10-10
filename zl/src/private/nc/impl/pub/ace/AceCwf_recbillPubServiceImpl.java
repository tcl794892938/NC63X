package nc.impl.pub.ace;

import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillInsertBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillUpdateBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillDeleteBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillSendApproveBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillUnSendApproveBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillApproveBP;
import nc.bs.zl.cwf_recbill.ace.bp.AceCwf_recbillUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_recbill.AggRecbillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCwf_recbillPubServiceImpl {
	// 新增
	public AggRecbillVO[] pubinsertBills(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggRecbillVO> transferTool = new BillTransferTool<AggRecbillVO>(
					clientFullVOs);
			// 调用BP
			AceCwf_recbillInsertBP action = new AceCwf_recbillInsertBP();
			AggRecbillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceCwf_recbillDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggRecbillVO[] pubupdateBills(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggRecbillVO> transferTool = new BillTransferTool<AggRecbillVO>(
					clientFullVOs);
			AceCwf_recbillUpdateBP bp = new AceCwf_recbillUpdateBP();
			AggRecbillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggRecbillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggRecbillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggRecbillVO> query = new BillLazyQuery<AggRecbillVO>(
					AggRecbillVO.class);
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
	public AggRecbillVO[] pubsendapprovebills(
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills)
			throws BusinessException {
		AceCwf_recbillSendApproveBP bp = new AceCwf_recbillSendApproveBP();
		AggRecbillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggRecbillVO[] pubunsendapprovebills(
			AggRecbillVO[] clientFullVOs, AggRecbillVO[] originBills)
			throws BusinessException {
		AceCwf_recbillUnSendApproveBP bp = new AceCwf_recbillUnSendApproveBP();
		AggRecbillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggRecbillVO[] pubapprovebills(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_recbillApproveBP bp = new AceCwf_recbillApproveBP();
		AggRecbillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggRecbillVO[] pubunapprovebills(AggRecbillVO[] clientFullVOs,
			AggRecbillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_recbillUnApproveBP bp = new AceCwf_recbillUnApproveBP();
		AggRecbillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}