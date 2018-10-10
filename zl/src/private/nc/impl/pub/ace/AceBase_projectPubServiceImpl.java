package nc.impl.pub.ace;

import nc.bs.zl.base_project.ace.bp.AceBase_projectInsertBP;
import nc.bs.zl.base_project.ace.bp.AceBase_projectUpdateBP;
import nc.bs.zl.base_project.ace.bp.AceBase_projectDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.base_project.AggProjectVO;

public abstract class AceBase_projectPubServiceImpl {
	// 新增
	public AggProjectVO[] pubinsertBills(AggProjectVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggProjectVO> transferTool = new BillTransferTool<AggProjectVO>(
					vos);
			AggProjectVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceBase_projectInsertBP action = new AceBase_projectInsertBP();
			AggProjectVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggProjectVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggProjectVO> transferTool = new BillTransferTool<AggProjectVO>(
					vos);
			AggProjectVO[] fullBills = transferTool.getClientFullInfoBill();
			AceBase_projectDeleteBP deleteBP = new AceBase_projectDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggProjectVO[] pubupdateBills(AggProjectVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggProjectVO> transTool = new BillTransferTool<AggProjectVO>(
					vos);
			// 补全前台VO
			AggProjectVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggProjectVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceBase_projectUpdateBP bp = new AceBase_projectUpdateBP();
			AggProjectVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggProjectVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggProjectVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggProjectVO> query = new BillLazyQuery<AggProjectVO>(
					AggProjectVO.class);
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

}