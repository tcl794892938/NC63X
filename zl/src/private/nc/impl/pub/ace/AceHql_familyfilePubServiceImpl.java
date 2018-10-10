package nc.impl.pub.ace;

import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileInsertBP;
import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileUpdateBP;
import nc.bs.zl.hql_familyfile.ace.bp.AceHql_familyfileDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_familyfile.AggFamilyfileVO;

public abstract class AceHql_familyfilePubServiceImpl {
	// 新增
	public AggFamilyfileVO[] pubinsertBills(AggFamilyfileVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggFamilyfileVO> transferTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			AggFamilyfileVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceHql_familyfileInsertBP action = new AceHql_familyfileInsertBP();
			AggFamilyfileVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggFamilyfileVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggFamilyfileVO> transferTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			AggFamilyfileVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_familyfileDeleteBP deleteBP = new AceHql_familyfileDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggFamilyfileVO[] pubupdateBills(AggFamilyfileVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggFamilyfileVO> transTool = new BillTransferTool<AggFamilyfileVO>(
					vos);
			// 补全前台VO
			AggFamilyfileVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggFamilyfileVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceHql_familyfileUpdateBP bp = new AceHql_familyfileUpdateBP();
			AggFamilyfileVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggFamilyfileVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggFamilyfileVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggFamilyfileVO> query = new BillLazyQuery<AggFamilyfileVO>(
					AggFamilyfileVO.class);
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