package nc.impl.pub.ace;

import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileInsertBP;
import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileUpdateBP;
import nc.bs.zl.hql_builldingfile.ace.bp.AceHql_builldingfileDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_builldingfile.AggBuildingfileVO;

public abstract class AceHql_builldingfilePubServiceImpl {
	// 新增
	public AggBuildingfileVO[] pubinsertBills(AggBuildingfileVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggBuildingfileVO> transferTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			AggBuildingfileVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceHql_builldingfileInsertBP action = new AceHql_builldingfileInsertBP();
			AggBuildingfileVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggBuildingfileVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggBuildingfileVO> transferTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			AggBuildingfileVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_builldingfileDeleteBP deleteBP = new AceHql_builldingfileDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggBuildingfileVO[] pubupdateBills(AggBuildingfileVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggBuildingfileVO> transTool = new BillTransferTool<AggBuildingfileVO>(
					vos);
			// 补全前台VO
			AggBuildingfileVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggBuildingfileVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceHql_builldingfileUpdateBP bp = new AceHql_builldingfileUpdateBP();
			AggBuildingfileVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggBuildingfileVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggBuildingfileVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggBuildingfileVO> query = new BillLazyQuery<AggBuildingfileVO>(
					AggBuildingfileVO.class);
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