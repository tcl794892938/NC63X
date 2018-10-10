package nc.impl.pub.ace;

import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceInsertBP;
import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceUpdateBP;
import nc.bs.zl.ly_businesssource.ace.bp.AceLy_businesssourceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_businesssource.AggBusinessSourceVO;

public abstract class AceLy_businesssourcePubServiceImpl {
	// 新增
	public AggBusinessSourceVO[] pubinsertBills(AggBusinessSourceVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBusinessSourceVO> transferTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			AggBusinessSourceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceLy_businesssourceInsertBP action = new AceLy_businesssourceInsertBP();
			AggBusinessSourceVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBusinessSourceVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggBusinessSourceVO> transferTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			AggBusinessSourceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLy_businesssourceDeleteBP deleteBP = new AceLy_businesssourceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBusinessSourceVO[] pubupdateBills(AggBusinessSourceVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBusinessSourceVO> transTool = new BillTransferTool<AggBusinessSourceVO>(
					vos);
			// 补全前台VO
			AggBusinessSourceVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggBusinessSourceVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceLy_businesssourceUpdateBP bp = new AceLy_businesssourceUpdateBP();
			AggBusinessSourceVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBusinessSourceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBusinessSourceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBusinessSourceVO> query = new BillLazyQuery<AggBusinessSourceVO>(
					AggBusinessSourceVO.class);
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