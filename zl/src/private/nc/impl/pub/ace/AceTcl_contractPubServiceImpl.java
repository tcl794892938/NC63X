package nc.impl.pub.ace;

import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractInsertBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractUpdateBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractDeleteBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractSendApproveBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractUnSendApproveBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractApproveBP;
import nc.bs.zl.tcl_contract.ace.bp.AceTcl_contractUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.tcl_contract.AggContractVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceTcl_contractPubServiceImpl {
	// 新增
	public AggContractVO[] pubinsertBills(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggContractVO> transferTool = new BillTransferTool<AggContractVO>(
					clientFullVOs);
			// 调用BP
			AceTcl_contractInsertBP action = new AceTcl_contractInsertBP();
			AggContractVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceTcl_contractDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggContractVO[] pubupdateBills(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggContractVO> transferTool = new BillTransferTool<AggContractVO>(
					clientFullVOs);
			AceTcl_contractUpdateBP bp = new AceTcl_contractUpdateBP();
			AggContractVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggContractVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggContractVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggContractVO> query = new BillLazyQuery<AggContractVO>(
					AggContractVO.class);
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
		
	}

	// 提交
	public AggContractVO[] pubsendapprovebills(
			AggContractVO[] clientFullVOs, AggContractVO[] originBills)
			throws BusinessException {
		AceTcl_contractSendApproveBP bp = new AceTcl_contractSendApproveBP();
		AggContractVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggContractVO[] pubunsendapprovebills(
			AggContractVO[] clientFullVOs, AggContractVO[] originBills)
			throws BusinessException {
		AceTcl_contractUnSendApproveBP bp = new AceTcl_contractUnSendApproveBP();
		AggContractVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggContractVO[] pubapprovebills(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceTcl_contractApproveBP bp = new AceTcl_contractApproveBP();
		AggContractVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggContractVO[] pubunapprovebills(AggContractVO[] clientFullVOs,
			AggContractVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceTcl_contractUnApproveBP bp = new AceTcl_contractUnApproveBP();
		AggContractVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}