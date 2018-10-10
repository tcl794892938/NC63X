package nc.impl.pub.ace;

import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditInsertBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUpdateBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditDeleteBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditSendApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUnSendApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditApproveBP;
import nc.bs.zl.cwf_carconedit.ace.bp.AceCwf_carconeditUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.cwf_carconedit.AggCarconeditVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceCwf_carconeditPubServiceImpl {
	// 新增
	public AggCarconeditVO[] pubinsertBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggCarconeditVO> transferTool = new BillTransferTool<AggCarconeditVO>(
					clientFullVOs);
			// 调用BP
			AceCwf_carconeditInsertBP action = new AceCwf_carconeditInsertBP();
			AggCarconeditVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceCwf_carconeditDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCarconeditVO[] pubupdateBills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCarconeditVO> transferTool = new BillTransferTool<AggCarconeditVO>(
					clientFullVOs);
			AceCwf_carconeditUpdateBP bp = new AceCwf_carconeditUpdateBP();
			AggCarconeditVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCarconeditVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCarconeditVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCarconeditVO> query = new BillLazyQuery<AggCarconeditVO>(
					AggCarconeditVO.class);
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
	public AggCarconeditVO[] pubsendapprovebills(
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills)
			throws BusinessException {
		AceCwf_carconeditSendApproveBP bp = new AceCwf_carconeditSendApproveBP();
		AggCarconeditVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggCarconeditVO[] pubunsendapprovebills(
			AggCarconeditVO[] clientFullVOs, AggCarconeditVO[] originBills)
			throws BusinessException {
		AceCwf_carconeditUnSendApproveBP bp = new AceCwf_carconeditUnSendApproveBP();
		AggCarconeditVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggCarconeditVO[] pubapprovebills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_carconeditApproveBP bp = new AceCwf_carconeditApproveBP();
		AggCarconeditVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggCarconeditVO[] pubunapprovebills(AggCarconeditVO[] clientFullVOs,
			AggCarconeditVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceCwf_carconeditUnApproveBP bp = new AceCwf_carconeditUnApproveBP();
		AggCarconeditVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}