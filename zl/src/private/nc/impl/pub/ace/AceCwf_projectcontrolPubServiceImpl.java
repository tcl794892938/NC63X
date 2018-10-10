package nc.impl.pub.ace;

import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolInsertBP;
import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolUpdateBP;
import nc.bs.zl.cwf_projectcontrol.ace.bp.AceCwf_projectcontrolDeleteBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.zl.cwf_projectcontrol.AggProjectcontrol;

public abstract class AceCwf_projectcontrolPubServiceImpl {
	// 新增
	public AggProjectcontrol[] pubinsertBills(AggProjectcontrol[] vos)
			throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggProjectcontrol> transferTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			AggProjectcontrol[] mergedVO = transferTool.getClientFullInfoBill();

			// 调用BP
			AceCwf_projectcontrolInsertBP action = new AceCwf_projectcontrolInsertBP();
			AggProjectcontrol[] retvos = action.insert(mergedVO);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggProjectcontrol[] vos) throws BusinessException {
		try {
			// 加锁 比较ts
			BillTransferTool<AggProjectcontrol> transferTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			AggProjectcontrol[] fullBills = transferTool.getClientFullInfoBill();
			AceCwf_projectcontrolDeleteBP deleteBP = new AceCwf_projectcontrolDeleteBP();
			deleteBP.delete(fullBills);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggProjectcontrol[] pubupdateBills(AggProjectcontrol[] vos)
			throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggProjectcontrol> transTool = new BillTransferTool<AggProjectcontrol>(
					vos);
			// 补全前台VO
			AggProjectcontrol[] fullBills = transTool.getClientFullInfoBill();
			// 获得修改前vo
			AggProjectcontrol[] originBills = transTool.getOriginBills();
			// 调用BP
			AceCwf_projectcontrolUpdateBP bp = new AceCwf_projectcontrolUpdateBP();
			AggProjectcontrol[] retBills = bp.update(fullBills, originBills);
			// 构造返回数据
			retBills = transTool.getBillForToClient(retBills);
			return retBills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggProjectcontrol[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggProjectcontrol[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggProjectcontrol> query = new BillLazyQuery<AggProjectcontrol>(
					AggProjectcontrol.class);
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