package nc.impl.pub.ace;

import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyInsertBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUpdateBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyDeleteBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneySendApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUnSendApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyApproveBP;
import nc.bs.zl.ly_sgmoney.ace.bp.AceLy_sgmoneyUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.zl.ly_sgmoney.AggSgMoneyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceLy_sgmoneyPubServiceImpl {
	// 新增
	public AggSgMoneyVO[] pubinsertBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSgMoneyVO> transferTool = new BillTransferTool<AggSgMoneyVO>(
					clientFullVOs);
			// 调用BP
			AceLy_sgmoneyInsertBP action = new AceLy_sgmoneyInsertBP();
			AggSgMoneyVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceLy_sgmoneyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSgMoneyVO[] pubupdateBills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSgMoneyVO> transferTool = new BillTransferTool<AggSgMoneyVO>(
					clientFullVOs);
			AceLy_sgmoneyUpdateBP bp = new AceLy_sgmoneyUpdateBP();
			AggSgMoneyVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSgMoneyVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSgMoneyVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSgMoneyVO> query = new BillLazyQuery<AggSgMoneyVO>(
					AggSgMoneyVO.class);
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
	public AggSgMoneyVO[] pubsendapprovebills(
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills)
			throws BusinessException {
		AceLy_sgmoneySendApproveBP bp = new AceLy_sgmoneySendApproveBP();
		AggSgMoneyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggSgMoneyVO[] pubunsendapprovebills(
			AggSgMoneyVO[] clientFullVOs, AggSgMoneyVO[] originBills)
			throws BusinessException {
		AceLy_sgmoneyUnSendApproveBP bp = new AceLy_sgmoneyUnSendApproveBP();
		AggSgMoneyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggSgMoneyVO[] pubapprovebills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_sgmoneyApproveBP bp = new AceLy_sgmoneyApproveBP();
		AggSgMoneyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggSgMoneyVO[] pubunapprovebills(AggSgMoneyVO[] clientFullVOs,
			AggSgMoneyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceLy_sgmoneyUnApproveBP bp = new AceLy_sgmoneyUnApproveBP();
		AggSgMoneyVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}