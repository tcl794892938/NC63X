package nc.impl.pub.ace;

import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleInsertBP;
import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleUpdateBP;
import nc.bs.zl.ld_feescale.ace.bp.AceLd_feescaleDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_feescale.AggFeescaleVO;

public abstract class AceLd_feescalePubServiceImpl {
	// 新增
	public AggFeescaleVO[] pubinsertBills(AggFeescaleVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggFeescaleVO> transferTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			AggFeescaleVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceLd_feescaleInsertBP action = new AceLd_feescaleInsertBP();
			AggFeescaleVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggFeescaleVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggFeescaleVO> transferTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			AggFeescaleVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLd_feescaleDeleteBP deleteBP = new AceLd_feescaleDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggFeescaleVO[] pubupdateBills(AggFeescaleVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggFeescaleVO> transTool = new BillTransferTool<AggFeescaleVO>(
					vos);
			// 补全前台VO
			AggFeescaleVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggFeescaleVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceLd_feescaleUpdateBP bp = new AceLd_feescaleUpdateBP();
			AggFeescaleVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggFeescaleVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggFeescaleVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggFeescaleVO> query = new BillLazyQuery<AggFeescaleVO>(
					AggFeescaleVO.class);
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