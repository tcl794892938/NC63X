package nc.impl.pub.ace;

import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesInsertBP;
import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesUpdateBP;
import nc.bs.zl.cwf_sales.ace.bp.AceCwf_salesDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_sales.AggSalesVO;

public abstract class AceCwf_salesPubServiceImpl {
	// 新增
	public AggSalesVO[] pubinsertBills(AggSalesVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggSalesVO> transferTool = new BillTransferTool<AggSalesVO>(
					vos);
			AggSalesVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceCwf_salesInsertBP action = new AceCwf_salesInsertBP();
			AggSalesVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggSalesVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggSalesVO> transferTool = new BillTransferTool<AggSalesVO>(
					vos);
			AggSalesVO[] fullBills = transferTool.getClientFullInfoBill();
			AceCwf_salesDeleteBP deleteBP = new AceCwf_salesDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggSalesVO[] pubupdateBills(AggSalesVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggSalesVO> transTool = new BillTransferTool<AggSalesVO>(
					vos);
			// 补全前台VO
			AggSalesVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggSalesVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceCwf_salesUpdateBP bp = new AceCwf_salesUpdateBP();
			AggSalesVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggSalesVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggSalesVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggSalesVO> query = new BillLazyQuery<AggSalesVO>(
					AggSalesVO.class);
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