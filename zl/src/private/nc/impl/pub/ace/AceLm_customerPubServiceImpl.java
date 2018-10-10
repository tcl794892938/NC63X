package nc.impl.pub.ace;

import nc.bs.zl.lm_customer.ace.bp.AceLm_customerInsertBP;
import nc.bs.zl.lm_customer.ace.bp.AceLm_customerUpdateBP;
import nc.bs.zl.lm_customer.ace.bp.AceLm_customerDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.lm_customer.AggCustomerVO;

public abstract class AceLm_customerPubServiceImpl {
	// 新增
	public AggCustomerVO[] pubinsertBills(AggCustomerVO[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggCustomerVO> transferTool = new BillTransferTool<AggCustomerVO>(
					vos);
			AggCustomerVO[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceLm_customerInsertBP action = new AceLm_customerInsertBP();
			AggCustomerVO[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggCustomerVO[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggCustomerVO> transferTool = new BillTransferTool<AggCustomerVO>(
					vos);
			AggCustomerVO[] fullBills = transferTool.getClientFullInfoBill();
			AceLm_customerDeleteBP deleteBP = new AceLm_customerDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggCustomerVO[] pubupdateBills(AggCustomerVO[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggCustomerVO> transTool = new BillTransferTool<AggCustomerVO>(
					vos);
			// 补全前台VO
			AggCustomerVO[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggCustomerVO[] originBills = transTool.getOriginBills();
			// 调用BP
			AceLm_customerUpdateBP bp = new AceLm_customerUpdateBP();
			AggCustomerVO[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggCustomerVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggCustomerVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggCustomerVO> query = new BillLazyQuery<AggCustomerVO>(
					AggCustomerVO.class);
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