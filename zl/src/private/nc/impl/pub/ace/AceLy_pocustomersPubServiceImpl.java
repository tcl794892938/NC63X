package nc.impl.pub.ace;

import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersInsertBP;
import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersUpdateBP;
import nc.bs.zl.ly_pocustomers.ace.bp.AceLy_pocustomersDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ly_pocustomers.AggPocustomersVO;

public abstract class AceLy_pocustomersPubServiceImpl {
	// 新增
	public AggPocustomersVO[] pubinsertBills(AggPocustomersVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggPocustomersVO> transferTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			AggPocustomersVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceLy_pocustomersInsertBP action = new AceLy_pocustomersInsertBP();
			AggPocustomersVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggPocustomersVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggPocustomersVO> transferTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			AggPocustomersVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLy_pocustomersDeleteBP deleteBP = new AceLy_pocustomersDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggPocustomersVO[] pubupdateBills(AggPocustomersVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggPocustomersVO> transTool = new BillTransferTool<AggPocustomersVO>(
					vos);
			// 补全前台VO
			AggPocustomersVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggPocustomersVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceLy_pocustomersUpdateBP bp = new AceLy_pocustomersUpdateBP();
			AggPocustomersVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggPocustomersVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggPocustomersVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggPocustomersVO> query = new BillLazyQuery<AggPocustomersVO>(
					AggPocustomersVO.class);
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