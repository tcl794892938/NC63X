package nc.impl.pub.ace;

import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceInsertBP;
import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceUpdateBP;
import nc.bs.zl.ld_housesource.ace.bp.AceLd_housesourceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.ld_housesource.AggHousesourceVO;

public abstract class AceLd_housesourcePubServiceImpl {
	// 新增
	public AggHousesourceVO[] pubinsertBills(AggHousesourceVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggHousesourceVO> transferTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			AggHousesourceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceLd_housesourceInsertBP action = new AceLd_housesourceInsertBP();
			AggHousesourceVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggHousesourceVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggHousesourceVO> transferTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			AggHousesourceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLd_housesourceDeleteBP deleteBP = new AceLd_housesourceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggHousesourceVO[] pubupdateBills(AggHousesourceVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggHousesourceVO> transTool = new BillTransferTool<AggHousesourceVO>(
					vos);
			// 补全前台VO
			AggHousesourceVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggHousesourceVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceLd_housesourceUpdateBP bp = new AceLd_housesourceUpdateBP();
			AggHousesourceVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggHousesourceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggHousesourceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggHousesourceVO> query = new BillLazyQuery<AggHousesourceVO>(
					AggHousesourceVO.class);
			bills = query.query(queryScheme, " order by to_number(floorn) desc,to_number(unit) asc,to_number(roomnumber) asc");
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