package nc.impl.pub.ace;

import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceInsertBP;
import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceUpdateBP;
import nc.bs.zl.hql_jt_acceptance.ace.bp.AceHql_jt_acceptanceDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.hql_jt_acceptance.AggJt_acceptanceVO;

public abstract class AceHql_jt_acceptancePubServiceImpl {
	// 新增
	public AggJt_acceptanceVO[] pubinsertBills(AggJt_acceptanceVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggJt_acceptanceVO> transferTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			AggJt_acceptanceVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceHql_jt_acceptanceInsertBP action = new AceHql_jt_acceptanceInsertBP();
			AggJt_acceptanceVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggJt_acceptanceVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggJt_acceptanceVO> transferTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			AggJt_acceptanceVO[] fullBills = transferTool.getClientFullInfoBill();
			AceHql_jt_acceptanceDeleteBP deleteBP = new AceHql_jt_acceptanceDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggJt_acceptanceVO[] pubupdateBills(AggJt_acceptanceVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggJt_acceptanceVO> transTool = new BillTransferTool<AggJt_acceptanceVO>(
					vos);
			// 补全前台VO
			AggJt_acceptanceVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggJt_acceptanceVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceHql_jt_acceptanceUpdateBP bp = new AceHql_jt_acceptanceUpdateBP();
			AggJt_acceptanceVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggJt_acceptanceVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggJt_acceptanceVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggJt_acceptanceVO> query = new BillLazyQuery<AggJt_acceptanceVO>(
					AggJt_acceptanceVO.class);
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